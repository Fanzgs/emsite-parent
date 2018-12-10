/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.service.TreeService;
import com.empire.emsite.modules.sys.dao.AreaDao;
import com.empire.emsite.modules.sys.entity.Area;

/**
 * 类AreaService.java的实现描述：区域Service
 * 
 * @author arron 2017年10月30日 下午4:32:17
 */
@Service
@Transactional(readOnly = true)
public class AreaService extends TreeService<AreaDao, Area> {
    @Autowired
    private AreaDao areaDao;

    public List<Area> findAllList() {
        return areaDao.findAllList(new Area());
    }

    @Override
    @Transactional(readOnly = false)
    public void save(Area area) {
        super.save(area);
        //UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Area area) {
        super.delete(area);
        //UserUtils.removeCache(UserUtils.CACHE_AREA_LIST);
    }

}
