package com.spring.web;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author jun
 * @date 2018/12/19
 */
public class Hello  implements Controller {

    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        //1.创建modelandView
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg1","hellowolrd~..");
        mav.addObject("msg2","hello,springmvc..");

        mav.addObject("att","klkjkj");
        //封装视图
        mav.setViewName("hello");
        //返回modelandview
        return mav;
    }



}
