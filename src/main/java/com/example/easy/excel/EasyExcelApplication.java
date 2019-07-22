package com.example.easy.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easy.excel.dao")
public class EasyExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelApplication.class, args);
        System.out.println("导出数据库请访问 http://localhost:8080/user/export");
        System.out.println("导入数据库请访问 http://localhost:8080/index");
    }

}
