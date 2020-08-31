package com.t271.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        Singleton3 s1=Singleton3.getInstance();
        Singleton3 s2=Singleton3.getInstance();
        Singleton3 s3=Singleton3.getInstance();
        Singleton3 s4=Singleton3.getInstance();
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);

    }
}
