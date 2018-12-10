/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.test.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.modules.test.dao.TestDao;
import com.empire.emsite.modules.test.entity.Test;

/**
 * 类TestService.java的实现描述：测试Service
 * 
 * @author arron 2017年10月30日 下午4:36:43
 */
@Service
@Transactional(readOnly = true)
public class TestService extends CrudService<TestDao, Test> {

}
