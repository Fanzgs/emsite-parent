/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.sys.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.modules.sys.entity.Area;
import com.empire.emsite.modules.sys.facade.AreaFacadeService;
import com.empire.emsite.modules.sys.service.AreaService;

/**
 * 类AreaFacadeServiceImpl.java的实现描述：区域FacadeService接口实现
 * 
 * @author arron 2017年9月17日 下午9:53:27
 */
@Service
public class AreaFacadeServiceImpl implements AreaFacadeService {
    @Autowired
    private AreaService areaService;

    @Override
    public List<Area> findAllList() {
        return areaService.findAllList();
    }

    @Override
    public void save(Area area) {
        areaService.save(area);
    }

    @Override
    public void delete(Area area) {
        areaService.delete(area);
    }

    @Override
    public Area get(String id) {
        return areaService.get(id);
    }

}
