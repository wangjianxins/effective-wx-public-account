package com.effective.wechat.public_account.enums;

/**
 * 微信菜单按钮枚举类
 */
public enum WxButtonType {

    /**
     * 需要那些事件才微信文档中查找添加
     */
    CLICK("click","点击事件"),
    VIEW("view","跳转url事件"),
    SCANCODE_PUSH("scancode_push","扫码事件"),

    ;


    /**
     * 微信按钮的类型
     */
    private final String buttonType;
    /**
     * 名称解释
     */
    private final String name;
    public String getButtonType() {
        return buttonType;
    }
    public String getName() {
        return name;
    }

    WxButtonType(String buttonType, String name) {
        this.buttonType = buttonType;
        this.name = name;
    }

}
