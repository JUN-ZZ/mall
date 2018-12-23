package com.mall.controller;

import com.mall.model.User;
import com.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jun
 * @date 2018/12/23
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService = null;


    @ResponseBody
    @RequestMapping("/hello")
    public String  hello(Model model){

//        List<User> list = userService.listAllUser();
//        model.addAttribute("list",list);

        String username = userService.queryUserNameById(1);
        model.addAttribute("username",username);
        return "hello";
    }


    @RequestMapping("/login.do")
    public String login(){
        return "index";
    }



}
