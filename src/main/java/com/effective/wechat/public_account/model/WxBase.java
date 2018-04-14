package com.effective.wechat.public_account.model;

/**
 * 微信基础实体类
 */
public class WxBase {

    private String appid;
    private String appsecret;

    public WxBase(String appid,String appsecret){
        this.appid = appid;
        this.appsecret = appsecret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

}
