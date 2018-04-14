package com.effective.wechat.public_account.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * accessToken实体类
 */
public class AccessToken {

    private String accessToken;
    private Integer expiresIn;
    private Long expiresTime;
    protected Lock accessTokenLock = new ReentrantLock();


    public AccessToken(String accessToken,Integer expiresIn){
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        //转化毫秒
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

    public Lock getAccessTokenLock() {
        return accessTokenLock;
    }



}
