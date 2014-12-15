package com.koala.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by li on 2014/12/13.
 */
public class HttpReq {
    public static void main(String[] a) {
        System.out.println(JsonParser.parser(executeHttpGet("")));
    }

    public static String executeHttpGet(String condition) {
        String result = null;
        URL url = null;
        HttpURLConnection connection = null;
        InputStreamReader in = null;
        try {    //http://10.237.114.95:9090/search?type=%E8%B4%A7%E5%B8%81%E5%9F%BA%E9%87%91
            String httpUrl = "http://10.237.114.95:9090/search?offset=0&size=20";
            if(!condition.isEmpty()){
                httpUrl = httpUrl + condition;
            }
            url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            in = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(in);
            StringBuffer strBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                strBuffer.append(line);
            }
            result = strBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return result;
    }
}
