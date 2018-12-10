/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.testwx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.modules.testwx.dao.TestWxDataDao;
import com.empire.emsite.modules.testwx.entity.TestWxData;

/**
 * 类TestWxDataService.java的实现描述：bbService
 * 
 * @author arron 2017年10月30日 下午4:37:39
 */
@Service
@Transactional(readOnly = true)
public class TestWxDataService extends CrudService<TestWxDataDao, TestWxData> {

    public TestWxData get(String id) {
        return super.get(id);
    }

    public List<TestWxData> findList(TestWxData testWxData) {
        return super.findList(testWxData);
    }

    public Page<TestWxData> findPage(Page<TestWxData> page, TestWxData testWxData) {
        return super.findPage(page, testWxData);
    }

    public List<TestWxData> findPageList(Page<TestWxData> page, TestWxData testWxData) {
        return super.findPage(page, testWxData).getList();
    }

    @Transactional(readOnly = false)
    public void save(TestWxData testWxData) {
        super.save(testWxData);
    }

    @Transactional(readOnly = false)
    public void delete(TestWxData testWxData) {
        super.delete(testWxData);
    }

}
