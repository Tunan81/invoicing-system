package team.tunan.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @author Tunan
 * @version 1.0
 * 代码生成器
 */
public class CodeGenerator {
    public static void main(String[] args) {
        generate();
    }

    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/jxc?serverTimezone=GMT%2b8&useSSL=false", "root", "261615")
                .globalConfig(builder -> {
                    builder.author("Tunan") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            // todo 更改为你自己的路径
                            .outputDir("E:\\Code\\java_code\\invoicing-system");
                })
                .packageConfig(builder -> {
                    builder.parent("team.tunan") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            // todo 更改为你自己的路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E:\\Code\\java_code\\invoicing-system")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude("provider") // 设置需要生成的表名
                            .addTablePrefix(); // 设置过滤表前缀
                })
                .execute();

    }
}
