package com.spring.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author jun
 * @date 2018/12/21
 */
@ControllerAdvice
public class ControlerAdviceTest {

    @ExceptionHandler
    public void exceptionhandler(Exception e){
        System.out.println(e);
        System.out.println("全局异常。。。");
    }

    @ExceptionHandler
    public void exceptionhandler(ArithmeticException e){
        System.out.println(e);
        System.out.println("全局异常。。。");
    }

    @ExceptionHandler
    public void exceptionhandler(NullPointerException e){
        System.out.println(e);
        System.out.println("全局异常。。。");
    }



}
