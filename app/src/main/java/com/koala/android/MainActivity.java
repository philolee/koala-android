package com.koala.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private static Handler handler = new Handler();
    private static SimpleAdapter listItemAdapter;
    private static ArrayList<HashMap<String, Object>> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitDropList.initChoice(this);
        initUi();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUi("");
    }


    void initUi() {
        ListView list = (ListView) findViewById(R.id.lv_msg_list);
        listItem = new ArrayList<HashMap<String, Object>>();
        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItem,//数据源
                R.layout.lay_main,
                new String[]{"ItemTitle", "ItemText", "ItemRate"},
                new int[]{R.id.item_title, R.id.item_content, R.id.item_rate}
        );

        //添加并且显示
        list.setAdapter(listItemAdapter);
        //添加点击
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
            }
        });
    }

    static void updateUi(final String condition) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = HttpReq.executeHttpGet(condition);
                if (result != null) {
                    listItem.clear();
                    listItem.addAll(JsonParser.parser(result));
                    handler.post(runnableUi);
                }
                Log.d(TAG, result);
            }
        }).start();
    }

    static Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            //更新界面
            listItemAdapter.notifyDataSetChanged();
        }

    };
}
