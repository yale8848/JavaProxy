package ren.yale.javaproxy.javassist.proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import ren.yale.javaproxy.jdk.UserService;
import ren.yale.javaproxy.jdk.UserServiceImpl;

import java.lang.reflect.Method;

/**
 * Created by Yale on 2017/6/16.
 */
public class Main {

    public static void main(String args[]){

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setSuperclass(UserServiceImpl.class);

        Class<ProxyObject> proxyClass = proxyFactory.createClass();

        try {
            UserService proxyTest = (UserService) proxyClass.newInstance();



            ((ProxyObject) proxyTest).setHandler(new MethodHandler() {

                // 真实主題
                UserService test = new UserServiceImpl();

                public Object invoke(Object self, Method thisMethod,
                                     Method proceed, Object[] args) throws Throwable {
                    String before = "before ";
                    Object str = thisMethod.invoke(test, args);
                    String after = " after";
                    return before + str + after;
                }
            });
            System.out.println(proxyTest.getName(1));

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
}
