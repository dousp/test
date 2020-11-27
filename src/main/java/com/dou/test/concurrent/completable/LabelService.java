package com.dou.test.concurrent.completable;

public class LabelService implements RemoteLoader {
    @Override
    public String load() {
        this.delay();
        return "标签信息";
    }
}
