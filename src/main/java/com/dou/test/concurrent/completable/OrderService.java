package com.dou.test.concurrent.completable;

public class OrderService implements RemoteLoader {
    @Override
    public String load() {
        this.delay();
        return "订单信息";
    }
}
