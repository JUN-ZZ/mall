package com.mall.service;

/**
 * @author jun
 * @date 2018/12/19
 */

import com.mall.domain.User;
import com.mall.exception.MsgException;
import com.mall.util.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * UserSerivceImpl的代理类
 */
public class UserServiceImplProxy {
    private final UserServiceImpl userService = new UserServiceImpl();

    public UserService getUserServiceProxy(){
        return (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("开启事务");
                            TransactionManager.startTrans();
                            Object o = method.invoke(userService,args);
                            TransactionManager.commitTrans();
                            System.out.println("提交事务");
                            return o;
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("回滚事务");
                            TransactionManager.rollBack();
                        }finally {
                            TransactionManager.closeConn();
                        }
                        return null;
                    }
                });
    }

//    public static void main(String[] args){
//        UserServiceImplProxy proxy = new UserServiceImplProxy();
//        UserService userService = proxy.getUserServiceProxy();
//
//
//    }



}
