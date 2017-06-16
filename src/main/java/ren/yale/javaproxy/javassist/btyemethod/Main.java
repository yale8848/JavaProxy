package ren.yale.javaproxy.javassist.btyemethod;

import javassist.*;
import ren.yale.javaproxy.jdk.UserService;
import ren.yale.javaproxy.jdk.UserServiceImpl;

/**
 * Created by Yale on 2017/6/16.
 */
public class Main {

    public static void main(String args[]) throws Exception{

        ClassPool classPool = new ClassPool(true);
        String className = UserServiceImpl.class.getName();

        CtClass ctClass = classPool.makeClass(className + "JavassitProxy");

        // 添加接口,可选
        // ctClass.addInterface(classPool.get(RayTestInterface.class.getName()));

        // 添加超类
        ctClass.setSuperclass(classPool.get(UserServiceImpl.class.getName()));

        // 添加默认构造函数
        ctClass.addConstructor(CtNewConstructor.defaultConstructor(ctClass));

        // 添加屬性
        ctClass.addField(CtField.make("public " + className + " real = new "
                + className + "();", ctClass));

        // 添加方法,里面进行动态代理logic
        ctClass.addMethod(CtNewMethod
                .make("public String getName(){ return \"before \" + real.getName() + \" after\";}",
                        ctClass));

        Class<UserService> testClass = ctClass.toClass();
        UserService test = testClass.newInstance();
        test.getName(1);


    }
}
