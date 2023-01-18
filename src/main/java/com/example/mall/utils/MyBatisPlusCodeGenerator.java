package com.example.mall.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Component
public class MyBatisPlusCodeGenerator {
    public void generator() {
        FastAutoGenerator.create("jdbc:mysql://1.15.228.108:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&userSSL=false",
                        "fanfanService",
                        "123456")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://Github//demo"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.mall") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://Github//demo")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("demo"); // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
