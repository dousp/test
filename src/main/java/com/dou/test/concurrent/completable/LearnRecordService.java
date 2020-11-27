package com.dou.test.concurrent.completable;

public class LearnRecordService implements RemoteLoader {

    public String load() {
        this.delay();
        return "学习信息";
    }

}
