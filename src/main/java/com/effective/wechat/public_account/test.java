package com.effective.wechat.public_account;

import com.effective.wechat.public_account.service.WxBaseService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

@RestController
public class test extends HttpServlet {

    @Autowired
    private WxBaseService wxBaseService;

    @RequestMapping("/wx")
    @ResponseBody
    public String wx(){
        return wxBaseService.getAccessToken();
    }


    @RequestMapping("/test")
    public  void test(){
        WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
        config.setAppId("..."); // 设置微信公众号的appid
        config.setSecret("..."); // 设置微信公众号的app corpSecret

        WxMpService wxService = new WxMpServiceImpl();// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
        wxService.setWxMpConfigStorage(config);
        WxMpService wxMpService = new WxMpServiceHttpClientImpl();
        String url  = "jjjj";
        wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);

        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken("code");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        try {
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        boolean valid = wxMpService.oauth2validateAccessToken(wxMpOAuth2AccessToken);

    }


}
