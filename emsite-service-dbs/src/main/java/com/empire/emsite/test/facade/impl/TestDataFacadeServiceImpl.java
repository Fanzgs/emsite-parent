/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.facade.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.sys.entity.Area;
import com.empire.emsite.modules.sys.entity.Office;
import com.empire.emsite.modules.sys.entity.User;
import com.empire.emsite.test.dto.TestDataDTO;
import com.empire.emsite.test.entity.TestData;
import com.empire.emsite.test.facade.TestDataFacadeService;
import com.empire.emsite.test.service.TestDataService;

/**
 * 类TestDataFacadeServiceImpl.java的实现描述：单表生成FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:59:30
 */
@Service
public class TestDataFacadeServiceImpl implements TestDataFacadeService {

    @Autowired
    private TestDataService testDataService;

    //    @Override
    //    public List<TestData> findList(TestData testData) {
    //        return testDataService.findList(testData);
    //    }

    @Override
    public Page<TestDataDTO> findPage(Page page, TestDataDTO testDataDTO) {
        TestData entity = new TestData();
        try {
            BeanUtils.copyProperties(entity, testDataDTO);
            List<TestData> listOrigin = testDataService.findPageList(page, entity);
            List<TestDataDTO> listDestination = new ArrayList<TestDataDTO>();
            for (TestData source : listOrigin) {
                TestDataDTO target = new TestDataDTO();
                BeanUtils.copyProperties(target, source);
                if (source.getArea() != null) {
                    target.setAreaId(source.getArea().getId());
                    target.setAreaName(source.getArea().getName());
                }
                if (source.getOffice() != null) {
                    target.setOfficeId(source.getOffice().getId());
                    target.setOfficeName(source.getOffice().getName());
                }
                if (source.getUser() != null) {
                    target.setUserId(source.getUser().getId());
                    target.setUserName(source.getUser().getName());
                }
                listDestination.add(target);
            }
            page.setList(listDestination);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public TestDataDTO get(String id) {
        TestData entity = testDataService.get(id);
        TestDataDTO entityDTO = new TestDataDTO();
        try {
            BeanUtils.copyProperties(entityDTO, entity);
            if (entity.getArea() != null) {
                entityDTO.setAreaId(entity.getArea().getId());
                entityDTO.setAreaName(entity.getArea().getName());
            }
            if (entity.getOffice() != null) {
                entityDTO.setOfficeId(entity.getOffice().getId());
                entityDTO.setOfficeName(entity.getOffice().getName());
            }
            if (entity.getUser() != null) {
                entityDTO.setUserId(entity.getUser().getId());
                entityDTO.setUserName(entity.getUser().getName());
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return entityDTO;
    }

    @Override
    public void save(TestDataDTO testDataDTO) {
        TestData entity = null;
        try {
            if (StringUtils.isNotBlank(testDataDTO.getId())) {
                entity = testDataService.get(testDataDTO.getId());
            } else {
                entity = new TestData();
            }
            BeanUtils.copyProperties(entity, testDataDTO);
            Area area = new Area();
            area.setId(testDataDTO.getAreaId());
            Office office = new Office();
            office.setId(testDataDTO.getOfficeId());
            User user = new User();
            user.setId(testDataDTO.getUserId());
            entity.setArea(area);
            entity.setOffice(office);
            entity.setUser(user);
            testDataService.save(entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void delete(TestDataDTO testDataDTO) {
        TestData entity = testDataService.get(testDataDTO.getId());
        testDataService.delete(entity);
    }
}
