/**
 * Copyright &copy; 2017 <a href="https://gitee.com/hackempire/emsite-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.modules.gen.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empire.emsite.common.persistence.Page;
import com.empire.emsite.common.service.BaseService;
import com.empire.emsite.common.utils.StringUtils;
import com.empire.emsite.modules.gen.dao.GenSchemeDao;
import com.empire.emsite.modules.gen.dao.GenTableColumnDao;
import com.empire.emsite.modules.gen.dao.GenTableDao;
import com.empire.emsite.modules.gen.entity.GenConfig;
import com.empire.emsite.modules.gen.entity.GenScheme;
import com.empire.emsite.modules.gen.entity.GenTable;
import com.empire.emsite.modules.gen.entity.GenTableColumn;
import com.empire.emsite.modules.gen.entity.GenTemplate;
import com.empire.emsite.modules.gen.util.GenDbsUtils;
import com.empire.emsite.modules.sys.entity.User;

/**
 * 类GenSchemeService.java的实现描述：生成方案Service
 * 
 * @author arron 2017年10月30日 下午4:26:54
 */
@Service
@Transactional(readOnly = true)
public class GenSchemeService extends BaseService {

    @Autowired
    private GenSchemeDao      genSchemeDao;
    //	@Autowired
    //	private GenTemplateDao genTemplateDao;
    @Autowired
    private GenTableDao       genTableDao;
    @Autowired
    private GenTableColumnDao genTableColumnDao;

    public GenScheme get(String id) {
        return genSchemeDao.get(id);
    }

    public Page<GenScheme> find(Page<GenScheme> page, GenScheme genScheme) {
        GenDbsUtils.getTemplatePath();
        genScheme.setPage(page);
        page.setList(genSchemeDao.findList(genScheme));
        return page;
    }

    @Transactional(readOnly = false)
    public String save(GenScheme genScheme, User user) {
        if (StringUtils.isBlank(genScheme.getId())) {
            genScheme.preInsert();
            genSchemeDao.insert(genScheme);
        } else {
            genScheme.preUpdate();
            genSchemeDao.update(genScheme);
        }
        // 生成代码
        if ("1".equals(genScheme.getFlag())) {
            return generateCode(genScheme, user);
        }
        return "";
    }

    @Transactional(readOnly = false)
    public void delete(GenScheme genScheme) {
        genSchemeDao.delete(genScheme);
    }

    private String generateCode(GenScheme genScheme, User user) {

        StringBuilder result = new StringBuilder();

        // 查询主表及字段列
        GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
        genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(genTable.getId()))));

        // 获取所有代码模板
        GenConfig config = GenDbsUtils.getConfig();

        // 获取模板列表
        List<GenTemplate> templateList = GenDbsUtils.getTemplateList(config, genScheme.getCategory(), false);
        List<GenTemplate> childTableTemplateList = GenDbsUtils.getTemplateList(config, genScheme.getCategory(), true);

        // 如果有子表模板，则需要获取子表列表
        if (childTableTemplateList.size() > 0) {
            GenTable parentTable = new GenTable();
            parentTable.setParentTable(genTable.getName());
            genTable.setChildList(genTableDao.findList(parentTable));
        }

        // 生成子表模板代码
        for (GenTable childTable : genTable.getChildList()) {
            childTable.setParent(genTable);
            childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(new GenTable(childTable.getId()))));
            genScheme.setGenTable(childTable);
            Map<String, Object> childTableModel = GenDbsUtils.getDataModel(genScheme, user);
            for (GenTemplate tpl : childTableTemplateList) {
                result.append(GenDbsUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
            }
        }

        // 生成主表模板代码
        genScheme.setGenTable(genTable);
        Map<String, Object> model = GenDbsUtils.getDataModel(genScheme, user);
        for (GenTemplate tpl : templateList) {
            result.append(GenDbsUtils.generateToFile(tpl, model, genScheme.getReplaceFile()));
        }
        return result.toString();
    }
}
