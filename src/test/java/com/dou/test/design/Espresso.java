package com.dou.test.design;

public class Espresso extends Beverage{

    public Espresso() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1;
    }
}
