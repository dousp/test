package com.dou.test.design;

/**
 * 适配器模式
 * 饮料
 */
public abstract class Beverage {

    protected String description = "Unknown Beverage";

    public abstract double cost();

    public String getDescription() {
        return description;
    }

}
