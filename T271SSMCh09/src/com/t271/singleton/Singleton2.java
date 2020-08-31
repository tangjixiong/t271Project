package com.t271.singleton;

/**
 * 单例模式：饿汉模式
 */
public class Singleton2 {
    //1、构造函数私有化
    private Singleton2(){}

    private static Singleton2 singleton=new Singleton2();
    //2、提供一个静态方法给外部调用，获取当前类的对象
    public  static Singleton2 getInstance(){
        return singleton;
    }
}
