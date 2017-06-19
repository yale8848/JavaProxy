package ren.yale.javaproxy.javassist.btyemethod.Hello;

import javassist.*;
import ren.yale.javaproxy.jdk.UserServiceImpl;

/**
 * Created by Yale on 2017/6/16.
 */
public class Main {

    private static void hello() throws Exception{
        //Hello orig = new Hello();
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("ren.yale.javaproxy.javassist.btyemethod.Hello.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertAt(2,"{ System.out.println(\"Hello.say():\"); }");
        //m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        cc.writeFile();
        Class c = cc.toClass(cp.getClass().getClassLoader());
        Hello h = (Hello)c.newInstance();
        h.say();
    }


    public static void main(String args[]) throws Exception{

        hello();


    }
}
