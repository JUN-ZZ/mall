package com.mall.Aspect;

import com.mall.util.TransactionManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author jun
 * @date 2018/12/19
 */

@Component
@Aspect
public class TransAspect {


    @Before("execution(* com.mall.service..*(..))")
    public void StartTrans(JoinPoint point){
        String s = point.getSignature().toString();
        System.out.println("开启事务"+s);
        TransactionManager.startTrans();
    }

    @AfterThrowing("execution(* com.mall.service..*(..))")
    public void rollBack(){
        System.out.println("回滚事务");
        TransactionManager.rollBack();
    }

    @AfterReturning("execution(* com.mall.service..*(..))")
    public void CommitTrans(){
        System.out.println("提交事务");
        TransactionManager.commitTrans();
    }

//    @After("execution(* com.mall.service..*(..))")
//    public void closeConn(){
//        TransactionManager.closeConn();
//    }




}
