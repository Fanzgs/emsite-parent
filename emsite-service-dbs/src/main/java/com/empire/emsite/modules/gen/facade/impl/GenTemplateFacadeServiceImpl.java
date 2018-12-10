/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.modules.gen.entity.GenTemplate;
import com.empire.emsite.modules.gen.facade.GenTemplateFacadeService;
import com.empire.emsite.modules.gen.service.GenTemplateService;

/**
 * 类GenTemplateFacadeServiceImpl.java的实现描述：代码模板FacadeService接口实现类
 * 
 * @author arron 2017年9月17日 下午9:50:04
 */
@Service
public class GenTemplateFacadeServiceImpl implements GenTemplateFacadeService {
    @Autowired
    private GenTemplateService genTemplateService;

    @Override
    public GenTemplate get(String id) {
        return genTemplateService.get(id);
    }

    @Override
    public Page<GenTemplate> find(Page<GenTemplate> page, GenTemplate genTemplate) {
        return genTemplateService.find(page, genTemplate);
    }

    @Override
    public void save(GenTemplate genTemplate) {
        genTemplateService.save(genTemplate);
    }

    @Override
    public void delete(GenTemplate genTemplate) {
        genTemplateService.delete(genTemplate);
    }
}
