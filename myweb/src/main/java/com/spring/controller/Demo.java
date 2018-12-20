package com.spring.controller;

import com.spring.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jun
 * @date 2018/12/20
 */

@Controller
public class Demo {


    @RequestMapping("/demo.action")
    public String say(Model model){
        model.addAttribute("msg","hello demo springmvc...");
        return "demo";
//        return "";
    }

    @RequestMapping("/hello1.action")
    public String  hello1(HttpServletRequest request,
                          HttpServletResponse response,Model model){

        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println(username+"age:"+age);
        model.addAttribute("username",username);
        model.addAttribute("age",age);

        return "hello1";
    }

    @RequestMapping("/hello2.action")
    public String hello2( String username, int age, String gender, Model model){

        model.addAttribute("username",username);
        model.addAttribute("age",age);
        model.addAttribute("gender",gender);

        return "hello2";
    }


    @RequestMapping("/hello3")
    public String hello3(User user,Model model){

        model.addAttribute("user",user);

        return "hello3";
    }


}
