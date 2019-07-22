package com.example.easy.excel.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController:跳到首页
 *
 * @author zhangxiaoxiang
 * @date: 2019/07/21
 */
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        System.out.println("进入首页");
        return "index";
    }

}
