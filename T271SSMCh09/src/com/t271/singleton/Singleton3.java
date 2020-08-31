package com.t271.singleton;

/**
 * 单例模式：静态内部类
 */
public class Singleton3 {
    //1、构造函数私有化
    private Singleton3(){}

    private static Singleton3 singleton;
    //2、提供一个静态方法给外部调用，获取当前类的对象
    public  static Singleton3 getInstance(){
        singleton=SingtonHelper.INSTANCE;
        return singleton;
    }

    public  static  class SingtonHelper{
        private static  final Singleton3  INSTANCE=new Singleton3();
    }
}
