package ren.yale.javaproxy;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import ren.yale.javaproxy.javassist.btyemethod.Hello.Hello;
import retrofit2.Retrofit;

/**
 * Created by Yale on 2017/6/16.
 */
public class Main {

    public static void main(String args[]) throws Exception{
        Retrofit retrofit;

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("retrofit2.retrofit");
        CtMethod m = cc.getDeclaredMethod("say");
        StringBuffer body = new StringBuffer();

        body.append("say($$);\n");

        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass(cp.getClass().getClassLoader());
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
