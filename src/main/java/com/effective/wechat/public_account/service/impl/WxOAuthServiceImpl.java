package com.effective.wechat.public_account.service.impl;

import com.effective.wechat.public_account.httpclient.HttpClientUtils;
import com.effective.wechat.public_account.model.WxBase;
import com.effective.wechat.public_account.model.WxOAuth;
import com.effective.wechat.public_account.model.WxUserInfo;
import com.effective.wechat.public_account.service.WxOAuthService;
import com.effective.wechat.public_account.wxjson.WxOAuthJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 微信公众号网页授权实现类
 * @author wangjianxin
 */
@Service
public class WxOAuthServiceImpl implements WxOAuthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Value("${wx.oauth.getCode.url}")
    private String getCodeUrl;
    @Value("${wx.oauth.redirect.url}")
    private String redirectUri;
    @Value("${wx.oauth.getAccessToken.url}")
    private String oauthAccessTokenUrl;
    @Value("${wx.oauth.userInfo.url}")
    private String userInfoUrl;

    @Autowired
    private WxOAuthJson wxOAuthJson;


    @Override
    public String oauth2LoginUrl() {
        String url = "";
        try {
            url = String.format(getCodeUrl,WxBaseServiceImpl.wxBase.getAppid(),redirectUri,"snsapi_userinfo");

        }catch (Exception e){
        }
        return url;

    }

    @Override
    public WxOAuth oauth2AccessToken(String code) {
        String oauthAccessToken = "";
        WxBase wxBase = WxBaseServiceImpl.wxBase;
        WxOAuth wxOAuth = null;
        if(null == wxBase){

        }
        try {
            String url = String.format(oauthAccessTokenUrl,wxBase.getAppid(),wxBase.getAppsecret(),code);
            String result = HttpClientUtils.getToUrl(url);
            wxOAuth =  wxOAuthJson.oauthAccessTokn(result);

        }catch (Exception e){
            e.printStackTrace();
        }

        return wxOAuth;
    }

    @Override
    public WxUserInfo getUserInfo(WxOAuth wxOAuth) {
        String url = String.format(userInfoUrl,wxOAuth.getAccessToken(),wxOAuth.getOpenid());
        String result = HttpClientUtils.getToUrl(url);
        logger.info("获取用户详细信息={}",result);
        return wxOAuthJson.userInfoJson(result);
    }


}
