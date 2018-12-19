package com.mall.service;

import com.mall.domain.Prod;
import com.mall.util.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jun
 * @date 2018/12/19
 */
public class ProdServiceImplProxy  {

    private final ProdServiceImpl prodService = new ProdServiceImpl();

    public ProdService getProdServceImplProxy(){
        Object proxyInstance = Proxy.newProxyInstance(ProdService.class.getClassLoader(), ProdServiceImpl.class.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        try {
                            System.out.println("开启事务。。。");
                            TransactionManager.startTrans();
                            Object o = method.invoke(prodService,args);
                            System.out.println("提交事务");
                            TransactionManager.commitTrans();
                            return o;
                        }catch (Exception e){
                            System.out.println("出现异常,回滚事务");
                            TransactionManager.rollBack();
                        }finally {
                            //关闭所有
                            TransactionManager.closeConn();
                        }
                        return null;
                    }
                });
        return  (ProdService)proxyInstance;

    }

}
