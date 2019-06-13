package com.dou.test.redis.limit;

/**
 * @author
 * 场景： 点赞、 评论、 上传等
 */
public abstract class FrequencyLimit {

    /**
     *  操作频率限制
     * @param action        操作
     * @param requestId     标识,，用户名或其他
     * @param period  限制额时间短周期
     * @param times    限制操作次数
     * @param type  超出限制后封印的时间类型，在指定时间解封或者指定n秒后解封
     * @param expireTime 解封时间
     * @return
     */
    public abstract Boolean actLimit(String action, int requestId, int period, int times, int type, String expireTime);

}
