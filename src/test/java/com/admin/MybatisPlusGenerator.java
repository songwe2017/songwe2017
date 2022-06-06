package com.admin;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.Test;

/**
 * @author Songwe
 * @since 2022/6/3 18:05
 */
public class MybatisPlusGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/user?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8",
            "root",
            "1234")
            .build();

    /**
     * 策略配置
     */
    private StrategyConfig.Builder strategyConfig() {
        return new StrategyConfig.Builder()
                //.addTablePrefix("edu_")
                .addInclude("user") // 设置需要生成的表名
                .addInclude("role")
                .addInclude("permission")
                .addInclude("user_role_rel")
                .addInclude("role_perm_rel")
                ;
    }
    /**
     * 全局配置
     */
    private GlobalConfig.Builder globalConfig() {
        return new GlobalConfig.Builder().fileOverride()
                .outputDir("D://workspace//wrokspace-spring//songwe2017//src//main//java")
                .author("songwe")
                .disableOpenDir()
                .enableSwagger();
    }

    /**
     * 包配置
     */
    private PackageConfig.Builder packageConfig() {
        return new PackageConfig.Builder()
                .parent("com.admin")
                //.moduleName("eduservice")
                .entity("model");
    }

    /**
     * 生成文件
     */
    @Test
    public void testGenerate() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig()
                .entityBuilder()
                .logicDeleteColumnName("is_deleted") // 逻辑删除字段设置 新增@TableLogic注解
                .addTableFills(new Column("gmt_create", FieldFill.INSERT))
                .addTableFills(new Column("gmt_modified", FieldFill.INSERT_UPDATE))
                .superClass("com.admin.common.base.BaseModel") //基础父类名
                .addSuperEntityColumns("id", "is_deleted", "gmt_create", "gmt_modified")
                .enableLombok()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .controllerBuilder()
                .enableRestStyle()
                .build());
        generator.packageInfo(packageConfig().build());
        generator.global(globalConfig().build());
        generator.execute();
    }


}
