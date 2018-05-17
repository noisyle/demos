package com.noisyle.demo.jnidemo;

public class HelloWorld {
    static {
        System.loadLibrary("HelloWorld");
    }

    public native String sayHello(String name);

    public static void main(String[] args) {
        String name = "World";
        HelloWorld h = new HelloWorld();
        System.out.println(h.sayHello(name));
    }
}
