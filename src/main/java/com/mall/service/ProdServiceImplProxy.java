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
                            System.out.println("�������񡣡���");
                            TransactionManager.startTrans();
                            Object o = method.invoke(prodService,args);
                            System.out.println("�ύ����");
                            TransactionManager.commitTrans();
                            return o;
                        }catch (Exception e){
                            System.out.println("�����쳣,�ع�����");
                            TransactionManager.rollBack();
                        }finally {
                            //�ر�����
                            TransactionManager.closeConn();
                        }
                        return null;
                    }
                });
        return  (ProdService)proxyInstance;

    }

}
