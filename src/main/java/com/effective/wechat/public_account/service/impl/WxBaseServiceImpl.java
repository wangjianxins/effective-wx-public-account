package com.effective.wechat.public_account.service.impl;

import com.effective.wechat.public_account.httpclient.HttpClientUtils;
import com.effective.wechat.public_account.model.AccessToken;
import com.effective.wechat.public_account.model.WxBase;
import com.effective.wechat.public_account.service.WxBaseService;
import com.effective.wechat.public_account.wxjson.WxBaseJson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 微信基础接口的实现类
 * @author wangjianxin
 * @date 2018/04/14
 */
@Service
public class WxBaseServiceImpl implements WxBaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${wx.accessToken.url}")
    private String getAccessTokenUrl;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.appsecret}")
    private String appsecret;

    @Autowired
    private WxBaseJson wxBaseJson;

    private String paramAccesstokenUrl;
    public static WxBase wxBase;
    private volatile static AccessToken accessToken;


    @Override
    public String getAccessToken() {
        if(null != accessToken){
            if(!accessToken.isAccessTokenExpired()){
                return accessToken.getAccessToken();
            }
        }
        String url = this.getAccessTokenUrl();
        String result = HttpClientUtils.getToUrl(url);
        logger.info("获取accessToken结果={}",result);

        try {
            accessToken = wxBaseJson.accessTokenJson(result);
        }catch (Exception e){
            return e.getMessage();
        }

        return  accessToken.getAccessToken();
    }

    public String getAccessTokenUrl(){
        if(StringUtils.isNoneBlank(paramAccesstokenUrl)){
            return paramAccesstokenUrl;
        }

        wxBase = new WxBase(appid,appsecret);
        final String url = String.format(getAccessTokenUrl,appid,appsecret);
        paramAccesstokenUrl = url;
        return url;
    }


}
