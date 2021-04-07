package com.algos.TST;

public class App {

    public static void main(String[] args) {
        TST tst = new TST();
        tst.put("apple", 1);
        tst.put("orange", 2);

        System.out.println("Value of String apple: " + tst.get("apple"));
        System.out.println("Value of String orange: " + tst.get("orange"));
    }

}
