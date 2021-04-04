package com.rohan.java.core;

enum Animals {

    DOG("woof"),
    CAT("meow");

    String sound;

    Animals(String sound) {
        this.sound = sound;
    }

}

public class EnumTest {
    static Animals a;

    /**
     * https://docs.oracle.com/javase/specs/jls/se7/html/jls-15.html#jls-15.11
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(a.DOG.sound);
    }
}