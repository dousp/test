package com.dou.test.redis.distributed.one;

import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * @author dsp
 * @date 2019-08-19
 */
public class RateLimitScript implements RedisScript<String> {

    static {

        // StringBuilder builder = new StringBuilder(2048);
        // builder.append(" StringBuilder builder = new StringBuilder(2048); ");
        // builder.append(" local ratelimit_info = redis.pcall('HMGET',KEYS[1],'last_time','current_token') ");
        // builder.append(" local last_time = ratelimit_info[1] ");
        // builder.append(" local current_token = tonumber(ratelimit_info[2]) ");
        // builder.append(" local max_token = tonumber(ARGV[1]) ");
        // builder.append(" local token_rate = tonumber(ARGV[2]) ");
        // builder.append(" local current_time = tonumber(ARGV[3]) ");
        // builder.append(" local reverse_time = 1000/token_rate ");
        // builder.append(" if current_token == nil then ");
        // builder.append("         current_token = max_token ");
        // builder.append("         last_time = current_time ");
        // builder.append(" else ");
        // builder.append(" local past_time = current_time-last_time ");
        // builder.append(" local reverse_token = math.floor(past_time/reverse_time) ");
        // builder.append(" current_token = current_token+reverse_token ");
        // builder.append(" last_time = reverse_time*reverse_token+last_time ");
        // builder.append(" if current_token>max_token then ");
        // builder.append("         current_token = max_token ");
        // builder.append(" end ");
        // builder.append("         end ");
        // builder.append(" local result = 0 ");
        // builder.append(" if(current_token>0) then ");
        // builder.append("         result = 1 ");
        // builder.append(" current_token = current_token-1 ");
        // builder.append(" end ");
        // builder.append(" redis.call('HMSET',KEYS[1],'last_time',last_time,'current_token',current_token) ");
        // builder.append(" redis.call('pexpire',KEYS[1],math.ceil(reverse_time*(max_token-current_token)+(current_time-last_time))) ");
        // builder.append(" return result ");

                // builder.append(" local ratelimit_info = redis.pcall('HMGET',KEYS[1],'last_time','current_token') ")
                //            .append(" local last_time = ratelimit_info[1] ")
                //            .append(" local current_token = tonumber(ratelimit_info[2]) ")
                //            .append(" local max_token = tonumber(ARGV[1]) ")
                //            .append(" local token_rate = tonumber(ARGV[2]) ")
                //            .append(" local current_time = tonumber(ARGV[3]) ")
                //            .append(" local reverse_time = 1000/token_rate ")
                //            .append(" if current_token == nil then ")
                //                    .append(" current_token = max_token ")
                //                    .append(" last_time = current_time ")
                //            .append(" else ")
                //                    .append(" local past_time = current_time-last_time ")
                //                    .append(" local reverse_token = math.floor(past_time/reverse_time) ")
                //                    .append(" current_token = current_token+reverse_token ")
                //                    .append(" last_time = reverse_time*reverse_token+last_time ")
                //                    .append(" if current_token>max_token then ")
                //                         .append(" current_token = max_token ")
                //                    .append(" end ")
                //            .append(" end ")
                //            .append(" local result = 0 ")
                //            .append(" if(current_token>0) then ")
                //                    .append(" result = 1 ")
                //                    .append(" current_token = current_token-1 ")
                //            .append(" end ")
                //            .append(" redis.call( 'HMSET',KEYS[1], 'last_time',last_time,'current_token',current_token) ")
                //            .append(" redis.call( 'pexpire',KEYS[1], math.ceil(reverse_time*(max_token-current_token)+(current_time-last_time))) ")
                //            .append(" return result ");

    }

    private static final String SCRIPT =
               " local ratelimit_info = redis.pcall('HMGET',KEYS[1],'last_time','current_token') "
            + " local last_time = ratelimit_info[1] "
            + " local current_token = tonumber(ratelimit_info[2]) "
            + " local max_token = tonumber(ARGV[1]) "
            + " local token_rate = tonumber(ARGV[2]) "
            + " local current_time = tonumber(ARGV[3]) "
            + " local reverse_time = 1000/token_rate " +
               " if current_token == nil then " +
                       " current_token = max_token " +
                       " last_time = current_time "
            + " else " +
                       " local past_time = current_time-last_time " +
                       " local reverse_token = math.floor(past_time/reverse_time) " +
                       " current_token = current_token+reverse_token " +
                       " last_time = reverse_time * reverse_token+last_time " +
                       " if current_token>max_token then " +
                               " current_token = max_token end " +
                       " end "
            + " local result = '0' " +
               " if(current_token>0) then result = '1' current_token = current_token-1  end "
            + " redis.call('HMSET',KEYS[1],'last_time',last_time,'current_token',current_token) "
            + " redis.call('pexpire',KEYS[1],math.ceil(reverse_time * (max_token-current_token)+(current_time-last_time))) "
            + " return result ";


    @Override
    public String getSha1() {
        return DigestUtils.sha1DigestAsHex(SCRIPT);
    }

    @Override
    public Class<String> getResultType() {
        return String.class;
    }

    @Override
    public String getScriptAsString() {
        return SCRIPT;
    }
}
