package com.coolweather.app.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by zeli-zheng on 2016/12/05.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                Log.d("test",address);
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    /*
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        response.append(line);
                    }
                     */

                    String response = "";
                    Scanner scanner = new Scanner(in, "UTF-8");
                    response = scanner.useDelimiter("\\A").next();
//                    Log.d("test",response);
                    scanner.close();
                    if (listener != null){
                        /*
                        //回调onFinish()方法
                        listener.onFinish(response.toString());
                        */
                        //回调onFinish()方法
                        listener.onFinish(response);
                    }
                } catch (Exception e){
                    if (listener != null){
                        //回调onError()方法
                        listener.onError(e);
                    }
                }finally {
                    if (connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();

    }
}
