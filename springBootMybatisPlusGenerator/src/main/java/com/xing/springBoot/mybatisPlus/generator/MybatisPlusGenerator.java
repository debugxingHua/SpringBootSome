package com.xing.springBoot.mybatisPlus.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 生成器
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/xinghua?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        // 新建数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url,"root","root").build();
        // 获取项目路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("项目路径：->"+projectPath);
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .author("x")//作者
                .outputDir(projectPath+"/src/main/java")//文件输出路径
                .disableOpenDir()//禁止打开输出目录 不禁用的话在生成完毕后自动打开你项目路径下文件夹
                .commentDate("yyyy-mm-dd")//指定注释日期格式化
                .commentDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)) // 也是注释日期
                //数据库时间类型到实体类时间类型 ，三种模式：1.ONLY_DATE-只使用 java.util.date 代替 2.SQL_PACK-使用 java.sql 包下的 3.默认TIME_PACK使用 java.time 包下的java8 新的时间类型
                .dateType(DateType.TIME_PACK)
//                .enableKotlin()//开启 kotlin 模式
//                .enableSwagger()//开启 swagger 模式 直接在实体上生成swagger的API注解
                .fileOverride()//覆盖已有文件
//                .openDir(false)
                .build();
        // 包相关的配置项
        PackageConfig packageConfig = new PackageConfig.Builder()
                .moduleName("generator")//指定模块名称
                .parent("com.xing.springBoot.mybatisPlus")//指定父包名
                .controller("controller")//默认就是controller 一般不用设置
                .entity("model")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                .other("other")//指定自定义文件包名
                .xml("mapper.xml")//指定xml包名
                .build();
        // 策略配置项
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addTablePrefix("t_")//增加表前缀
//                .addTableSuffix("")//增加表后缀
                .addFieldPrefix("c_")//增加字段前缀
                .addFieldSuffix()//增加过滤字段后缀
//                .addInclude("")//增加包含的表名 include和addExclude 只有存在一个
                .addExclude("test")//增加排除表
                .disableSqlFilter()//禁用sql过滤
                .enableSkipView()//开启跳过视图
                .build();
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.ENTITY)
                .entity("/templates/entity.java")
                .service("/templates/service.java")
                .serviceImpl("/templates/serviceImpl.java")
                .mapper("/templates/mapper.java")
                .mapperXml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
        // 注入配置
        InjectionConfig injectionConfig = new InjectionConfig.Builder().build();

        //特么的 这个AutoGenerator还没有加上注入templateConfig的方法
        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig, dataSourceConfig, strategyConfig, templateConfig, globalConfig, injectionConfig);

        //自动生成
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig)
//                .global(globalConfig)
//                .strategy(strategyConfig)
//                .packageInfo(packageConfig);
                .config(configBuilder);
        autoGenerator.execute();
    }
}
