package com.yjy.singleton.v2;

/**
 * 方式2：双重检查-单例模式
 */
public class Singleton {
    private volatile static Singleton singleton; //注意volatile

    private Singleton() {
    }

    public static final Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}