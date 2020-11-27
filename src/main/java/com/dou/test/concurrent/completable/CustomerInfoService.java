package com.dou.test.concurrent.completable;

public class CustomerInfoService implements RemoteLoader {

    public String load() {
        this.delay();
        return "基本信息";
    }

}
