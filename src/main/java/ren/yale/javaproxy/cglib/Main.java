package ren.yale.javaproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import ren.yale.javaproxy.jdk.UserService;
import ren.yale.javaproxy.jdk.UserServiceImpl;

/**
 * Created by Yale on 2017/6/16.
 */
public class Main {

    public static void main(String args[]){
        CglibProxy cglibProxy = new CglibProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(cglibProxy);

        UserService o = (UserService)enhancer.create();
        o.getName(1);
        o.getAge(1);
    }
}
