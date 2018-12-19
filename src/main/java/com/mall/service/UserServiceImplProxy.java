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
 * UserSerivceImpl�Ĵ�����
 */
public class UserServiceImplProxy {
    private final UserServiceImpl userService = new UserServiceImpl();

    public UserService getUserServiceProxy(){
        return (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("��������");
                            TransactionManager.startTrans();
                            Object o = method.invoke(userService,args);
                            TransactionManager.commitTrans();
                            System.out.println("�ύ����");
                            return o;
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("�ع�����");
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
