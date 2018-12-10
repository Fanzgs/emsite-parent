/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.testwx.facade.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.testwx.dto.TestWxDataDTO;
import com.empire.emsite.modules.testwx.entity.TestWxData;
import com.empire.emsite.modules.testwx.facade.TestWxDataFacadeService;
import com.empire.emsite.modules.testwx.service.TestWxDataService;

/**
 * 类TestWxDataFacadeServiceImpl.java的实现描述：bbFacadeServiceImpl
 * 
 * @author arron 2017年10月30日 下午4:37:25
 */
@Service
public class TestWxDataFacadeServiceImpl implements TestWxDataFacadeService {
    @Autowired
    private TestWxDataService testWxDataService;

    public TestWxDataDTO get(String id) {
        TestWxData entity = testWxDataService.get(id);
        TestWxDataDTO entityDTO = new TestWxDataDTO();
        try {
            BeanUtils.copyProperties(entityDTO, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return entityDTO;
    }

    public List<TestWxData> findList(TestWxData testWxData) {
        return testWxDataService.findList(testWxData);
    }

    public Page<TestWxDataDTO> findPage(Page page, TestWxDataDTO testWxDataDTO) {
        TestWxData entity = new TestWxData();
        try {
            BeanUtils.copyProperties(entity, testWxDataDTO);
            List<TestWxData> listOrigin = testWxDataService.findPageList(page, entity);
            List<TestWxDataDTO> listDestination = new ArrayList<TestWxDataDTO>();

            for (TestWxData source : listOrigin) {
                TestWxDataDTO target = new TestWxDataDTO();
                BeanUtils.copyProperties(target, source);
                listDestination.add(target);
            }
            page.setList(listDestination);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return page;
    }

    public void save(TestWxDataDTO testWxDataDTO) {
        TestWxData entity = null;
        try {
            if (StringUtils.isNotBlank(testWxDataDTO.getId())) {
                entity = testWxDataService.get(testWxDataDTO.getId());
            } else {
                entity = new TestWxData();
            }
            BeanUtils.copyProperties(entity, testWxDataDTO);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testWxDataService.save(entity);
    }

    public void delete(TestWxDataDTO testWxDataDTO) {
        TestWxData entity = testWxDataService.get(testWxDataDTO.getId());
        testWxDataService.delete(entity);
    }

}
