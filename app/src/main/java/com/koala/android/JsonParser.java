package com.koala.android;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by li on 2014/12/14.
 */
public class JsonParser {
    public static ArrayList<HashMap<String, Object>> parser(String result) {
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject lindenResult = jsonObject.getJSONObject("lindenResult");
            JSONArray jsonArray = lindenResult.getJSONArray("hits");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject hit = jsonArray.getJSONObject(i);
                String source = hit.getString("source");
                JSONObject sourceObject = new JSONObject(source);
                String title = sourceObject.getString("title");
                String rate = sourceObject.getString("expectedProfitRate");
                String investField = sourceObject.getString("investField");
                String risk = sourceObject.optString("risk", "");
                String tag = sourceObject.optString("tag", "");
                String content = tag + " " + risk + " " + investField;
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", title.split("-")[0]);
                map.put("ItemText", content);
                map.put("ItemRate", rate);
                listItem.add(map);
            }
        } catch (Exception e) {
            Log.w("json Parser", e);
        }
        return listItem;
    }
}
