package com.effective.wechat.public_account.service;

/**
 * 微信基础的接口
 * @author wangjianxin
 * @date 2018/04/14
 */
public interface WxBaseService {

    /**
     * 获取公众号的全局凭证
     * accessToken有效期为2小时，但是不同ip请求有效期为5分钟
     * @return
     */
    String getAccessToken();
}
