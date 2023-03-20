package com.yanxia.study.factory;

import com.yanxia.study.service.IAccountService;
import com.yanxia.study.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {
    public final void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private TransactionManager transactionManager;

    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(), accountService.getClass().getInterfaces(), new InvocationHandler() {

            /**
             * 添加事务的支持
             * @param proxy the proxy instance that the method was invoked on
             *
             * @param method the {@code Method} instance corresponding to
             * the interface method invoked on the proxy instance.  The declaring
             * class of the {@code Method} object will be the interface that
             * the method was declared in, which may be a superinterface of the
             * proxy interface that the proxy class inherits the method through.
             *
             * @param args an array of objects containing the values of the
             * arguments passed in the method invocation on the proxy instance,
             * or {@code null} if interface method takes no arguments.
             * Arguments of primitive types are wrapped in instances of the
             * appropriate primitive wrapper class, such as
             * {@code java.lang.Integer} or {@code java.lang.Boolean}.
             *
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object returnValue = null;
                try {
                    System.out.println("动态代理事务开始");
                    transactionManager.beginTransation();
                    returnValue = method.invoke(accountService, args);
                    System.out.println("动态代理事务commit");
                    transactionManager.commit();
                    return returnValue;
                } catch (Exception e) {
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                } finally {
                    transactionManager.release();
                }
            }
        });
    }

    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    private IAccountService accountService;
}
