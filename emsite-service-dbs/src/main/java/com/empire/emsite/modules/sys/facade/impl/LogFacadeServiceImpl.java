/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.sys.entity.Log;
import com.empire.emsite.modules.sys.facade.LogFacadeService;
import com.empire.emsite.modules.sys.service.LogService;

/**
 * 类LogFacadeServiceImpl.java的实现描述：日志FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:54:11
 */
@Service
public class LogFacadeServiceImpl implements LogFacadeService {
    @Autowired
    private LogService logService;

    @Override
    public Page<Log> findPage(Page<Log> page, Log log) {
        return logService.findPage(page, log);
    }

    @Override
    public void save(Log log) {
        logService.save(log);
    }

}
