package com.xing.springBootMybatisPlus.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class MybatisPlusGenerator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/xinghua?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url,"root","root").build();
        String projectPath = System.getProperty("user.dir");
        GlobalConfig globalConfig = new GlobalConfig.Builder().outputDir(projectPath+"/src/main/java").openDir(false).build();
        PackageConfig packageConfig = new PackageConfig.Builder().moduleName("springBootMybatisPlus").parent("com.xing").build();
        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig);
        autoGenerator.global(globalConfig).packageInfo(packageConfig);
        autoGenerator.execute();
    }
}
