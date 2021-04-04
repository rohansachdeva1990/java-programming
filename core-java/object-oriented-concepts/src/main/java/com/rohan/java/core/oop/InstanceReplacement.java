package com.rohan.java.core.oop;

/**
 * When you access static member using object's instance, the instance gets replace by Class. i.e this.strokeWidth would be replace with RoundCapGraph.strokeWidth
 * <p>
 * There will be no NullPointerException due to the instance replacement.
 * <p>
 * I found a reference to this in the Java Specification: Chapter 15, Section 11: Field Access Expressions.
 * <p>
 * Example 15.11.1-2. Receiver Variable Is Irrelevant For static Field Access
 * <p>
 * The following program demonstrates that a null reference may be used to access a class (static) variable without causing an exception
 * <p>
 * public class RoundCapGraph extends View {
 * static private int strokeWidth = 20;
 * <p>
 * public void setStrokeWidth(int strokeWidth){
 * RoundCapGraph roundCapGraph = null;
 * roundCapGraph.strokeWidth = strokeWidth; // NullPointerException?
 * //warning : static member 'com.example.ud.RoundCapGraph.strokeWidth' accessed via instance reference
 * }
 * }
 */

import static java.lang.Integer.MAX_VALUE;

public class InstanceReplacement {

    class Car {
        public static final String SPORTS = "sports";

        void start() {

        }
    }

    class BMW extends Car {
        public void start() {

        }
    }

    public static void main(String[] args) {
        Car c = null;
        System.out.println(c.SPORTS); // Will print "sports"
        System.out.println(MAX_VALUE);
    }
}