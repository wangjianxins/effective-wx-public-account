package com.effective.wechat.public_account.wxjson;

import com.alibaba.fastjson.JSONObject;

/**
 * json处理基类
 */
public class BaseJson {

    /**
     * 错误json
     * @param jsonObject
     */
    public void errorJson(JSONObject jsonObject,String name){
        Integer errcode = (Integer) jsonObject.get("errcode");
        String errmsg = (String) jsonObject.get("errmsg");
        throw new RuntimeException(name+"失败，错误码："+errcode+"，错误信息"+errmsg);
    }
}
