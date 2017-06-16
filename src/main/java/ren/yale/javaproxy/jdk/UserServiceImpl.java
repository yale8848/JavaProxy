package ren.yale.javaproxy.jdk;

/**
 * Created by Yale on 2017/6/16.
 */
public class UserServiceImpl implements UserService {
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
