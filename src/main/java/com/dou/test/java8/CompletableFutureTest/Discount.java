package com.dou.test.java8.CompletableFutureTest;

import static com.dou.test.java8.CompletableFutureTest.Shop2.delay;

/**
 * @author dsp
 * @date 2019-09-05
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
        private final int percentage;
        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    // 将折扣代码应用于商品最初的原始价格
    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    //模 拟 Discount服务响应的延迟
    private static double apply(double price, Code code) {
        delay();
        return Double.parseDouble(String.format("%.2f", price * (100 - code.percentage) / 100 ));
    }
}
