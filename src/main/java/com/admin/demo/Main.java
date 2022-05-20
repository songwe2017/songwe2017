package com.admin.demo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author Songwe
 * @date 2022/5/20 18:13
 */
public class Main {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/user?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8", "root", "1234")
                .globalConfig(builder -> {
                    builder.author("songwe") // 设置作者
                    .outputDir("D://workspace//wrokspace-spring//songwe2017//src//main//java") // 指定输出目录,固定到 //java
                    .fileOverride()
                    .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent("com.admin.demo");// 设置父包名;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("role") // 设置需要生成的表名
                    .serviceBuilder().formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    .entityBuilder().enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
