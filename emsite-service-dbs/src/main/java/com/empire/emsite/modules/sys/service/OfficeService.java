/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.service.BaseService;
import com.empire.emsite.common.service.TreeService;
import com.empire.emsite.modules.sys.dao.OfficeDao;
import com.empire.emsite.modules.sys.entity.Office;
import com.empire.emsite.modules.sys.entity.User;

/**
 * 类OfficeService.java的实现描述：机构Service
 * 
 * @author arron 2017年10月30日 下午4:34:29
 */
@Service
@Transactional(readOnly = true)
public class OfficeService extends TreeService<OfficeDao, Office> {
    public List<Office> findAllList() {
        return dao.findAllList(new Office());
    }

    public List<Office> findAll(User user) {
        List<Office> officeList = null;
        if (user.isAdmin()) {
            officeList = dao.findAllList(new Office());
        } else {
            Office office = new Office();
            office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
            officeList = dao.findList(office);
        }
        return officeList;
    }

    public List<Office> findList(Boolean isAll, User user) {
        List<Office> officeList = null;
        if ((isAll != null && isAll) || user.isAdmin()) {
            officeList = dao.findAllList(new Office());

        } else {
            Office office = new Office();
            office.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "a", ""));
            officeList = dao.findList(office);
        }
        return officeList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Office> findList(Office office) {
        if (office != null) {
            office.setParentIds(office.getParentIds() + "%");
            return dao.findByParentIdsLike(office);
        }
        return new ArrayList<Office>();
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Office office) {
        super.save(office);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Office office) {
        super.delete(office);
    }

}
