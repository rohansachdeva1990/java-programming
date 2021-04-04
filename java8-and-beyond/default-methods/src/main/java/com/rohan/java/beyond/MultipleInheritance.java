package com.rohan.java.beyond;

interface Clickable {
    default void click() {
        System.out.println("click");
    }

    default void print() {
        System.out.println("Clickable");
    }
}

interface Accessible {
    default void access() {
        System.out.println("access");
    }

    default void print() {
        System.out.println("Accessible");
    }
}

class Button implements Clickable, Accessible {

/*    public void print(){
        Clickable.super.print();
        Accessible.super.print();
    }*/

    @Override
    public void print() {
        System.out.println("Buttom printing");
    }
}

public class MultipleInheritance {

    public static void main(String[] args) {
        Button button = new Button();
        button.click();
        button.access();
        button.print();
    }
}