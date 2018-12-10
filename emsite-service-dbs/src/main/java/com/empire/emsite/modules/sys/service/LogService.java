/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.service.CrudService;
import com.empire.emsite.common.utils.DateUtils;
import com.empire.emsite.modules.sys.dao.LogDao;
import com.empire.emsite.modules.sys.entity.Log;

/**
 * 类LogService.java的实现描述：日志Service
 * 
 * @author arron 2017年10月30日 下午4:33:41
 */
@Service
@Transactional(readOnly = true)
public class LogService extends CrudService<LogDao, Log> {

    @Override
    public Page<Log> findPage(Page<Log> page, Log log) {

        // 设置默认时间范围，默认当前月
        if (log.getBeginDate() == null) {
            log.setBeginDate(DateUtils.setDays(DateUtils.parseDate(DateUtils.getDate()), 1));
        }
        if (log.getEndDate() == null) {
            log.setEndDate(DateUtils.addMonths(log.getBeginDate(), 1));
        }

        return super.findPage(page, log);

    }

}
