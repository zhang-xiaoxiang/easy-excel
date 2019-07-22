package com.example.easy.excel.controller;


import com.alibaba.excel.metadata.BaseRowModel;
import com.example.easy.excel.entity.db.AdminUserDB;
import com.example.easy.excel.entity.db.UserDB;
import com.example.easy.excel.service.AdminUserService;
import com.example.easy.excel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhangxiaoxiang
 * @since 2019-07-21
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 导出 Excel（一个 sheet）
     */
    @RequestMapping(value = "export", method = RequestMethod.GET)
    public void writeExcel(HttpServletResponse response) throws IOException {
        /**
         * 注意我这里直接使用mybatis plus的方法
         * 查询数据库所有用户信息集合
         */
        userService.exportUserExcel(response);
        log.info("恭喜,导出excel成功!");
    }

    /**
     * 导入 Excel（一个 sheet）
     *
     * @param excel       需要导入的excel
     * @param headLineNum 就是excel头部从第几行开始,默认从1行开始
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public Object readExcel(MultipartFile excel, @RequestParam(defaultValue = "1") int headLineNum) throws IOException {
        List<Object> userExcel = null;
        try {
            userExcel = userService.importUserExcel(excel, new UserDB(), headLineNum);
            if (userExcel == null) {
                log.error("插入数据库失败!");
                return "插入数据库失败!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("无法导入数据库,请查看配置参数是否正确!");
            return "无法导入数据库,请查看配置参数是否正确!";
        }
        log.info("恭喜,导入excel成功!");
        return userExcel;
    }


    /**
     * 读取 Excel(多个 sheet)
     * 当然也就是会存到多张表里面
     *
     * @param excel
     * @return
     * @throws IOException
     */
    @Transactional
    @RequestMapping(value = "manysheet", method = RequestMethod.POST)
    public Object readExcelmanysheet(MultipartFile excel, BaseRowModel model) throws IOException {
        List<Object> allSheet = new ArrayList<>();
        try {
            //事务控制一下
            List<Object> objects = userService.inportManySheet(excel, new UserDB(), 1, 1);
            List<Object> objects1 = adminUserService.inportManySheet(excel, new AdminUserDB(), 2, 1);
            allSheet.add(objects);
            allSheet.add(objects1);
            log.info("恭喜,导如excel(多sheet形式)成功!");
            return allSheet;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("无法导入数据库,请查看配置参数是否正确!");
            return "无法导入数据库,请查看配置参数是否正确!";
        }


    }

}




