/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.test.dao.TestDataDao;
import com.empire.emsite.test.entity.TestData;

/**
 * 类TestDataService.java的实现描述：单表生成Service
 * 
 * @author arron 2017年10月30日 下午4:40:57
 */
@Service
@Transactional(readOnly = true)
public class TestDataService extends CrudService<TestDataDao, TestData> {

    public TestData get(String id) {
        return super.get(id);
    }

    public List<TestData> findList(TestData testData) {
        return super.findList(testData);
    }

    public Page<TestData> findPage(Page<TestData> page, TestData testData) {
        return super.findPage(page, testData);
    }

    public List<TestData> findPageList(Page<TestData> page, TestData testData) {
        return super.findPage(page, testData).getList();
    }

    @Transactional(readOnly = false)
    public void save(TestData testData) {
        super.save(testData);
    }

    @Transactional(readOnly = false)
    public void delete(TestData testData) {
        super.delete(testData);
    }

}
