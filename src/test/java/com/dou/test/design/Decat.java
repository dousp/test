package com.dou.test.design;

public class Decat extends Beverage{

    public Decat() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 2;
    }
}
