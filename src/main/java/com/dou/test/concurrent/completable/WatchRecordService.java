package com.dou.test.concurrent.completable;

public class WatchRecordService implements RemoteLoader {
    @Override
    public String load() {
        this.delay();
        return "观看记录";
    }
}
