/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.common.utils.StringUtils;
import com.empire.emsite.test.dao.TestDataChildDao;
import com.empire.emsite.test.dao.TestDataMainDao;
import com.empire.emsite.test.entity.TestDataChild;
import com.empire.emsite.test.entity.TestDataMain;

/**
 * 类TestDataMainService.java的实现描述：主子表生成Service
 * 
 * @author arron 2017年10月30日 下午4:40:45
 */
@Service
@Transactional(readOnly = true)
public class TestDataMainService extends CrudService<TestDataMainDao, TestDataMain> {

    @Autowired
    private TestDataChildDao testDataChildDao;

    @Override
    public TestDataMain get(String id) {
        TestDataMain testDataMain = super.get(id);
        testDataMain.setTestDataChildList(testDataChildDao.findList(new TestDataChild(testDataMain)));
        return testDataMain;
    }

    @Override
    public List<TestDataMain> findList(TestDataMain testDataMain) {
        return super.findList(testDataMain);
    }

    @Override
    public Page<TestDataMain> findPage(Page<TestDataMain> page, TestDataMain testDataMain) {
        return super.findPage(page, testDataMain);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(TestDataMain testDataMain) {
        super.save(testDataMain);
        for (TestDataChild testDataChild : testDataMain.getTestDataChildList()) {
            if (testDataChild.getId() == null) {
                continue;
            }
            if (TestDataChild.DEL_FLAG_NORMAL.equals(testDataChild.getDelFlag())) {
                if (StringUtils.isBlank(testDataChild.getId())) {
                    testDataChild.setTestDataMain(testDataMain);
                    testDataChild.preInsert();
                    testDataChildDao.insert(testDataChild);
                } else {
                    testDataChild.preUpdate();
                    testDataChildDao.update(testDataChild);
                }
            } else {
                testDataChildDao.delete(testDataChild);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(TestDataMain testDataMain) {
        super.delete(testDataMain);
        testDataChildDao.delete(new TestDataChild(testDataMain));
    }

}
