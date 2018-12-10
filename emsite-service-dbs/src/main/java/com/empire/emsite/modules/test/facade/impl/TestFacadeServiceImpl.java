/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.test.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.test.entity.Test;
import com.empire.emsite.modules.test.facade.TestFacadeService;
import com.empire.emsite.modules.test.service.TestService;

/**
 * 类TestFacadeServiceImpl.java的实现描述：测试FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午10:02:54
 */
@Service
public class TestFacadeServiceImpl implements TestFacadeService {

    @Autowired
    private TestService testService;

    @Override
    public List<Test> findList(Test test) {
        return testService.findList(test);
    }

    @Override
    public Page<Test> findPage(Page<Test> page, Test test) {
        return testService.findPage(page, test);
    }

    @Override
    public Test get(String id) {
        return testService.get(id);
    }

    @Override
    public void save(Test test) {
        testService.save(test);
    }

    @Override
    public void delete(Test test) {
        testService.delete(test);
    }
}
