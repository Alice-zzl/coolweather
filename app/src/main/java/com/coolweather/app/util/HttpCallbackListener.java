package com.coolweather.app.util;

/**
 * Created by zeli-zheng on 2016/12/05.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
