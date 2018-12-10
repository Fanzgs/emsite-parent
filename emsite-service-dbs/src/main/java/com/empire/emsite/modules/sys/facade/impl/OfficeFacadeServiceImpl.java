/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.modules.sys.entity.Office;
import com.empire.emsite.modules.sys.entity.User;
import com.empire.emsite.modules.sys.facade.OfficeFacadeService;
import com.empire.emsite.modules.sys.service.OfficeService;

/**
 * 类OfficeFacadeServiceImpl.java的实现描述：机构FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:55:38
 */
@Service
public class OfficeFacadeServiceImpl implements OfficeFacadeService {
    @Autowired
    private OfficeService officeService;

    @Override
    public List<Office> findAllList() {
        return officeService.findAllList();
    }

    @Override
    public List<Office> findAll(User user) {
        return officeService.findAll(user);
    }

    @Override
    public List<Office> findList(Boolean isAll, User user) {
        return officeService.findList(isAll, user);
    }

    @Override
    public List<Office> findList(Office office) {
        return officeService.findList(office);
    }

    @Override
    public void save(Office office) {
        officeService.save(office);
    }

    @Override
    public void delete(Office office) {
        officeService.delete(office);
    }

    @Override
    public Office get(String id) {
        return officeService.get(id);
    }
}
