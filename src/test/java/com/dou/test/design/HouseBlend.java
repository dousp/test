package com.dou.test.design;

/**
 * HOUSE BLEND 翻译过来是家里面混合搅拌的意思，是北美特别流行的黑咖啡，它属于混合咖啡。
 */
public class HouseBlend extends Beverage {

    public HouseBlend(){
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
