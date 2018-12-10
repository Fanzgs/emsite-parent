/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.test.entity.TestDataMain;
import com.empire.emsite.test.facade.TestDataMainFacadeService;
import com.empire.emsite.test.service.TestDataMainService;

/**
 * 类TestDataMainFacadeServiceImpl.java的实现描述：主子表生成FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午10:00:03
 */
@Service
public class TestDataMainFacadeServiceImpl implements TestDataMainFacadeService {
    @Autowired
    private TestDataMainService testDataMainService;

    @Override
    public TestDataMain get(String id) {
        return testDataMainService.get(id);
    }

    @Override
    public List<TestDataMain> findList(TestDataMain testDataMain) {
        return testDataMainService.findList(testDataMain);
    }

    @Override
    public Page<TestDataMain> findPage(Page<TestDataMain> page, TestDataMain testDataMain) {
        return testDataMainService.findPage(page, testDataMain);
    }

    @Override
    public void save(TestDataMain testDataMain) {
        testDataMainService.save(testDataMain);
    }

    @Override
    public void delete(TestDataMain testDataMain) {
        testDataMainService.delete(testDataMain);
    }
}
