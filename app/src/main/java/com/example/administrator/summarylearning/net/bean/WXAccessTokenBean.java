package com.example.administrator.summarylearning.net.bean;

/**
 * @Author
 * @Time        2018/12/11 14:58
 * @Describe    微信根据code获取的access_token和openid
 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317851&token=328c299b9990b7a57a741c734ac44b8137a563e1&lang=zh_CN
 * @Modify
 */
public class WXAccessTokenBean {

    /**
     * access_token : ACCESS_TOKEN                  接口调用凭证
     * expires_in : 7200                            access_token接口调用凭证超时时间，单位（秒）
     * refresh_token : REFRESH_TOKEN                用户刷新access_token
     * openid : OPENID                              授权用户唯一标识
     * scope : SCOPE                                用户授权的作用域，使用逗号（,）分隔
     * unionid : o6_bmasdasdsad6_2sgVt7hMZOPfL      当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
