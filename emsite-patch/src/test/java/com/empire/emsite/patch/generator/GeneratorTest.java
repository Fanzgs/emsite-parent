/**
 * Copyright &copy; 2018 <a href="https://gitee.com/hackempire/patch-generator-parent">emsite</a> All rights reserved.
 */
package com.empire.emsite.patch.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNException;

import com.empire.patch.generator.GeneratePatchExecutor;
import com.empire.patch.generator.entity.GitProjectInfo;
import com.empire.patch.generator.entity.PatchInfo;
import com.empire.patch.generator.entity.ProjectReviseMapper;
import com.empire.patch.generator.entity.SourceMapper;
import com.empire.patch.generator.entity.SvnProjectInfo;
import com.empire.patch.generator.enums.GenTypeEnum;
import com.empire.patch.generator.enums.ProjectTypeEnum;
import com.empire.patch.generator.enums.VersionManagerTypeEnum;

/**
 * 类GeneratorTest.java的实现描述：生成器测试类
 * 
 * @author arron 2018年3月29日 下午5:29:39
 */
public class GeneratorTest {
    @Test
    public void testSvnGenerator() throws SVNException {
        SvnProjectInfo projectInfo = new SvnProjectInfo();
        projectInfo.setProjectName("ump20170420_chery_pc");
        projectInfo.setProjectType(ProjectTypeEnum.SINGLEMODULE);
        projectInfo.setTargetBaseDir("D:/SpringRooWorkSpace/ump20170420_chery_pc/");
        //设置svn用户名、命名
        projectInfo.setSvnPassword("111111111111111111");
        projectInfo.setSvnUsername("11111111111111111");
        projectInfo.setVersionManagerTypeEnum(VersionManagerTypeEnum.SVN);
        //设置svn地址
        projectInfo.setSvnUrl("https://---------------------/tags/ump20170420_chery_pc");
        projectInfo.setReviseMapper(new ProjectReviseMapper("/tags", ""));
        List<SourceMapper> sourceMappers = new ArrayList<>();
        SourceMapper javaMapper = new SourceMapper("/src/main/java", "target/classes", "/WEB-INF/classes");
        SourceMapper resourceMapper = new SourceMapper("/src/main/resources", "target/classes", "/WEB-INF/classes");
        SourceMapper webAppsMapper = new SourceMapper("/src/main/webapp", "src/main/webapp", "");
        sourceMappers.add(javaMapper);
        sourceMappers.add(resourceMapper);
        sourceMappers.add(webAppsMapper);

        projectInfo.setSourceMappers(sourceMappers);
        PatchInfo patchInfo = new PatchInfo();
        patchInfo.setStartVersion("14431");
        patchInfo.setEndVersion("14434");
        //可用于配置文件中
        String oldExcludeRevisions = "14432,14433";

        List<String> excludeRevisions = new ArrayList<String>();
        if (StringUtils.isNotBlank(oldExcludeRevisions)) {
            excludeRevisions = Arrays.asList(oldExcludeRevisions.split(","));
        }
        patchInfo.setExcludeRevisions(excludeRevisions);
        patchInfo.setPatchFileDir("D:/update/ump20170420_chery_pc");

        GeneratePatchExecutor.execute(projectInfo, patchInfo);
    }

    @Test
    public void testSvnLogGenerator() throws SVNException {
        SvnProjectInfo projectInfo = new SvnProjectInfo();
        projectInfo.setProjectName("ump20170420_chery_pc");
        projectInfo.setProjectType(ProjectTypeEnum.SINGLEMODULE);
        projectInfo.setTargetBaseDir("D:/SpringRooWorkSpace/ump20170420_chery_pc/");
        projectInfo.setVersionManagerTypeEnum(VersionManagerTypeEnum.SVN);
        List<SourceMapper> sourceMappers = new ArrayList<>();
        SourceMapper javaMapper = new SourceMapper("/src/main/java", "target/classes", "/WEB-INF/classes");
        SourceMapper resourceMapper = new SourceMapper("/src/main/resources", "target/classes", "/WEB-INF/classes");
        SourceMapper webAppsMapper = new SourceMapper("/src/main/webapp", "src/main/webapp", "");
        sourceMappers.add(javaMapper);
        sourceMappers.add(resourceMapper);
        sourceMappers.add(webAppsMapper);

        projectInfo.setSourceMappers(sourceMappers);
        PatchInfo patchInfo = new PatchInfo();
        patchInfo.setGenType(GenTypeEnum.LOG);
        //        patchInfo.setStartVersion("14431");
        //        patchInfo.setEndVersion("14434");
        //可用于配置文件中
        //        String oldExcludeRevisions = "14432,14433";

        //List<String> excludeRevisions = new ArrayList<String>();
        //        if (StringUtils.isNotBlank(oldExcludeRevisions)) {
        //            excludeRevisions = Arrays.asList(oldExcludeRevisions.split(","));
        //        }
        //        patchInfo.setExcludeRevisions(excludeRevisions);
        patchInfo.setPatchFileDir("D:/update/ump20170420_chery_pc");
        patchInfo.setPatchFile("E:/svn_aaron_update2016-11-10.txt");
        GeneratePatchExecutor.execute(projectInfo, patchInfo);
    }

    @Test
    public void testGitGenerator() throws SVNException {
        GitProjectInfo projectInfo = new GitProjectInfo();
        projectInfo.setProjectName("emsite");
        projectInfo.setProjectType(ProjectTypeEnum.MULTIMODULE);
        projectInfo.setTargetBaseDir("D:/Users/Administrator/git/emsite-parent/");

        projectInfo.setVersionManagerTypeEnum(VersionManagerTypeEnum.GIT);
        projectInfo.setGitRepositoryUrl("D:/Users/Administrator/git/emsite-parent/.git");

        List<SourceMapper> sourceMappers = new ArrayList<>();
        // 添加源码跟target跟生成patch的目录映射
        SourceMapper javaMapper = new SourceMapper("/src/main/java", "target/classes", "/WEB-INF/classes");
        SourceMapper resourceMapper = new SourceMapper("/src/main/resources", "target/classes", "/WEB-INF/classes");
        SourceMapper webAppsMapper = new SourceMapper("/src/main/webapp", "src/main/webapp", "");
        sourceMappers.add(javaMapper);
        sourceMappers.add(resourceMapper);
        sourceMappers.add(webAppsMapper);

        projectInfo.setSourceMappers(sourceMappers);
        PatchInfo patchInfo = new PatchInfo();
        patchInfo.setStartVersion("757212d");
        patchInfo.setEndVersion("544515f");
        patchInfo.setPatchFileDir("D:/update/emsite-parent");

        GeneratePatchExecutor.execute(projectInfo, patchInfo);
    }

    @Test
    public void testGitLogGenerator() throws SVNException {
        GitProjectInfo projectInfo = new GitProjectInfo();
        projectInfo.setProjectName("emsite");
        projectInfo.setProjectType(ProjectTypeEnum.MULTIMODULE);
        projectInfo.setTargetBaseDir("D:/Users/Administrator/git/emsite-parent/");

        projectInfo.setVersionManagerTypeEnum(VersionManagerTypeEnum.GIT);
        //projectInfo.setGitRepositoryUrl("D:/Users/Administrator/git/emsite-parent/.git");

        List<SourceMapper> sourceMappers = new ArrayList<>();
        // 添加源码跟target跟生成patch的目录映射
        SourceMapper javaMapper = new SourceMapper("/src/main/java", "target/classes", "/WEB-INF/classes");
        SourceMapper resourceMapper = new SourceMapper("/src/main/resources", "target/classes", "/WEB-INF/classes");
        SourceMapper webAppsMapper = new SourceMapper("/src/main/webapp", "src/main/webapp", "");
        sourceMappers.add(javaMapper);
        sourceMappers.add(resourceMapper);
        sourceMappers.add(webAppsMapper);

        projectInfo.setSourceMappers(sourceMappers);
        PatchInfo patchInfo = new PatchInfo();
        patchInfo.setGenType(GenTypeEnum.LOG);
        //        patchInfo.setStartVersion("757212d");
        //        patchInfo.setEndVersion("544515f");
        patchInfo.setPatchFileDir("D:/update/emsite-parent");
        patchInfo.setPatchFile("E:/git-aaron-update.txt");
        GeneratePatchExecutor.execute(projectInfo, patchInfo);
    }
}
