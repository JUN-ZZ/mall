package com.spring.controller;

import com.spring.domain.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jun
 * @date 2018/12/20
 */

@Controller
@SessionAttributes("username")
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
    public String hello2( @RequestParam(value = "un",required = false) String username, int age, String gender, Model model){

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


    //RESTFul风格的支持
    @RequestMapping("/hello4/{username}/{age}.action")
    public String hello4(@PathVariable String username,@PathVariable  int  age, Model model){
        model.addAttribute("username",username);
        model.addAttribute("age",age);

        return "hello4";
    }


    @RequestMapping("/hello5.action")
    public String hello5(){
        //实现转发
        return "forward:/hello3.action";
    }

    @RequestMapping("/hello6.action")
    public String hello6(){
        //实现重定向
        return "redirect:/hello3.action";
    }

//    实现session作用域的开发
    @RequestMapping("/hello7.action")
    public String hello7(String username,Model model){
        model.addAttribute("username",username);

        //实现重定向
        return "redirect:/hello3.action?username='军'";
    }


        @ResponseBody
        @RequestMapping("/hello8.action")
        public User hello8(HttpServletResponse response){
//            自动返回json格式的数据，但是需要导入相应的jar包
            User user = new User();
            user.setAge(18);
            user.setUsername("jun");
            return user;

    }



//    常用于Ajax请求
    @RequestMapping("/hello9.action")
    public void  hello9(HttpServletResponse response) throws IOException {
        response.getWriter().write("hello9 ..");
    }


//    表单文件上传
    @RequestMapping("/hello10.action")
    public String hello10(String username,@RequestParam(value = "tupian") MultipartFile multipartFile ,Model model) throws IOException {
        System.out.println(username);
        System.out.println(multipartFile.getOriginalFilename());
        String filename = multipartFile.getName();
        System.out.println(filename);
        InputStream in = multipartFile.getInputStream();
//        IOUtils.copy( );

        FileUtils.copyInputStreamToFile(in,new File("C:\\Users\\jun\\Desktop\\a\\"+multipartFile.getOriginalFilename()));


        return "hello";
    }

////    当发生异常时，同时配置有全局异常和局部异常，局部异常会覆盖全局异常
//    @ExceptionHandler
//    public void handlerException(Exception e){
//        System.out.println(e);
//        System.out.println("发生异常。。。");
//    }


    @RequestMapping("/error.action")
    public void error(){
        int i=6/0;

    }

    @RequestMapping("/error1.action")
    public void error1(){
        String s = null;
        System.out.println(s.length());

    }



}
