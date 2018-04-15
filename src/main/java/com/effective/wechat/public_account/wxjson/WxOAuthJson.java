package com.effective.wechat.public_account.wxjson;

import com.alibaba.fastjson.JSONObject;
import com.effective.wechat.public_account.model.WxOAuth;
import com.effective.wechat.public_account.model.WxUserInfo;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 微信网页授权json
 * @author wangjianxin
 */
@Component
public class WxOAuthJson extends BaseJson{

    /**
     * 解析
     * @param result
     * @return
     */
    public WxOAuth oauthAccessTokn(String result){

        JSONObject jsonObject = JSONObject.parseObject(result);
        String oauthAccessToken = (String) jsonObject.get("access_token");
        if(StringUtils.isEmpty(oauthAccessToken)){
            this.errorJson(jsonObject,"oauthAccessToken");
        }
        String openid = (String) jsonObject.get("openid");
        WxOAuth wxOAuth = new WxOAuth();
        wxOAuth.setAccessToken(oauthAccessToken);
        wxOAuth.setOpenid(openid);

        return wxOAuth;

    }

    public WxUserInfo userInfoJson(String result){
        WxUserInfo wxUserInfo = new WxUserInfo();
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer errcode = (Integer) jsonObject.get("errcode");
        if(null == errcode){

            wxUserInfo.setOpenid((String) jsonObject.get("openid"));
            wxUserInfo.setNickname((String) jsonObject.get("nickname"));
            wxUserInfo.setSex(String.valueOf((Integer) jsonObject.get("sex")));
            wxUserInfo.setProvince((String) jsonObject.get("province"));
            wxUserInfo.setCity((String) jsonObject.get("city"));
            wxUserInfo.setCountry((String) jsonObject.get("country"));
            wxUserInfo.setHeadImg((String) jsonObject.get("headimgurl"));
        }else{
            this.errorJson(jsonObject,"userInfo");
        }

        return wxUserInfo;

    }


}
