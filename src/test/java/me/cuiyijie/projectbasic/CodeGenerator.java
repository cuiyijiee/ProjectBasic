//package me.cuiyijie.projectbasic;
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//import me.cuiyijie.projectbasic.entity.BaseEntity;
//import org.junit.jupiter.api.Test;
//
//import java.util.Collections;
//
///**
// * @author cyj976655@gmail.com
// * @date 2022/5/16 22:07
// */
//public class CodeGenerator {
//
//    @Test
//    public void generatorTest() {
//
//        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/basic_project?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC",
//                        "root",
//                        "Abc,123.")
//                .globalConfig(builder -> {
//                    builder.author("cuiyijie")
//                            .enableSwagger()
//                            .fileOverride()
//                            .disableOpenDir()
//                            .outputDir(System.getProperty("user.dir") + "/src/main/java");
//                })
//                .packageConfig(builder -> {
//                    builder.parent("me.cuiyijie.projectbasic")
//                            .pathInfo(Collections.singletonMap(OutputFile.mapper, System.getProperty("user.dir") + "/src/main/resources/mapper"));
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("sys_user","sys_menu","sys_role","sys_user_role","sys_role_menu")
//                            .entityBuilder().superClass(BaseEntity.class)
//                            .enableLombok();
//                })
//                .templateEngine(new FreemarkerTemplateEngine())
//                .execute();
//    }
//
//}
