package com.wzc.requestpermissionlibrary;
/**
 * Created by wuzuchang On 2019/6/30
 **/
public interface PermissionCallBack {
    void agree(String permissions);
    void refusal(String permissions);
    void alwaysRefusal(String permissions);

}

