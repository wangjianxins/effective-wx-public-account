package com.effective.wechat.public_account.wxjson;

import com.alibaba.fastjson.JSONObject;
import com.effective.wechat.public_account.model.AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.concurrent.locks.Lock;

/**
 * 微信基础json工具类
 * @author wangjianxin
 */
@Component
public class WxBaseJson {

    /**
     * 解析获取到accessToken的返回值
     * @param res
     * @return
     */
    public AccessToken accessTokenJson(String res){

        if(StringUtils.isEmpty(res)){
            throw new NullPointerException("accessTokenJson res value is null");
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        String accessToken = (String) jsonObject.get("access_token");
        if(StringUtils.isEmpty(accessToken)){
            Integer errcode = (Integer) jsonObject.get("errcode");
            String errmsg = (String) jsonObject.get("errmsg");

            throw new RuntimeException("获取accessToken失败，错误码："+errcode+"，错误信息"+errmsg);
        }

        Integer expiresIn = (Integer) jsonObject.get("expires_in");
        AccessToken model = new AccessToken(accessToken,expiresIn);
        Lock lock = model.getAccessTokenLock();
        try {
            lock.lock();
            model.updateAccessToken(accessToken,expiresIn);

        }catch (Exception e){
            e.getCause();
        }finally {
            lock.unlock();
        }
        return model;
    }


}
