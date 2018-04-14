package com.effective.wechat.public_account.model;

/**
 * accessToken实体类
 */
public class AccessToken {

    private String accessToken;
    private Integer expiresIn;
    private Long expiresTime;

    public AccessToken(String accessToken,Integer expiresIn){
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}
