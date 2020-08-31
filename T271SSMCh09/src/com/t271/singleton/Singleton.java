package com.t271.singleton;

/**
 * 单例模式：懒汉模式
 */
public class Singleton {
    //1、构造函数私有化
    private  Singleton(){}

    private static   Singleton singleton;
    //2、提供一个静态方法给外部调用，获取当前类的对象
    public  static  Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
