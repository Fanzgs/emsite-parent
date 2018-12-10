/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.service.TreeService;
import com.empire.emsite.common.utils.StringUtils;
import com.empire.emsite.test.dao.TestTreeDao;
import com.empire.emsite.test.entity.TestTree;

/**
 * 类TestTreeService.java的实现描述：树结构生成Service
 * 
 * @author arron 2017年10月30日 下午4:41:31
 */
@Service
@Transactional(readOnly = true)
public class TestTreeService extends TreeService<TestTreeDao, TestTree> {

    public TestTree get(String id) {
        return super.get(id);
    }

    public List<TestTree> findList(TestTree testTree) {
        if (StringUtils.isNotBlank(testTree.getParentIds())) {
            testTree.setParentIds("," + testTree.getParentIds() + ",");
        }
        return super.findList(testTree);
    }

    @Transactional(readOnly = false)
    public void save(TestTree testTree) {
        super.save(testTree);
    }

    @Transactional(readOnly = false)
    public void delete(TestTree testTree) {
        super.delete(testTree);
    }

}
