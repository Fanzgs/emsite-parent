/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.test.entity.TestTree;
import com.empire.emsite.test.facade.TestTreeFacadeService;
import com.empire.emsite.test.service.TestTreeService;

/**
 * 类TestTreeFacadeServiceImpl.java的实现描述：树结构生成FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午10:00:50
 */
@Service
public class TestTreeFacadeServiceImpl implements TestTreeFacadeService {
    @Autowired
    private TestTreeService testTreeService;

    @Override
    public TestTree get(String id) {
        return testTreeService.get(id);
    }

    @Override
    public List<TestTree> findList(TestTree testTree) {
        return testTreeService.findList(testTree);
    }

    @Override
    public void save(TestTree testTree) {
        testTreeService.save(testTree);
    }

    @Override
    public void delete(TestTree testTree) {
        testTreeService.delete(testTree);
    }

}
