package com.rohan.java.core.oop;

class Alpha {
    static String s = "";

    protected Alpha(){
        s+= "alpha ";
    }
}

class SubAlpha extends Alpha {

    public SubAlpha (){
        s+="subalpha ";
    }

    @Override
    public String toString() {
        return s;
    }
}

public class ProtectedConstructor {
    public static void main(String[] args) {
        SubAlpha s = new SubAlpha();
        System.out.println(s);
    }
}