package com.koala.android;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by li on 2014/12/14.
 */
public class InitDropList {
    private static AtomicInteger typeSelected = new AtomicInteger(0);
    private static AtomicInteger rateSelected = new AtomicInteger(0);
    private static AtomicInteger MoneySelected = new AtomicInteger(0);
    private static AtomicInteger TimeSelected = new AtomicInteger(0);

    public static void initChoice(Activity activity) {
        createSpinner(activity, R.id.Spinner01, R.array.choiceConditionType, new TypeSpinnerSelectedListener());
        createSpinner(activity, R.id.Spinner02, R.array.choiceConditionRate, new RateSpinnerSelectedListener());
        createSpinner(activity, R.id.Spinner03, R.array.choiceConditionMoney, new MoneySpinnerSelectedListener());
        createSpinner(activity, R.id.Spinner04, R.array.choiceConditionTime, new TimeSpinnerSelectedListener());
    }

    private static void createSpinner(Activity activity, int spinnerId, int resourceId, AdapterView.OnItemSelectedListener spinnerSelectedListener) {
        Spinner spinner = (Spinner) activity.findViewById(spinnerId);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(activity, resourceId, R.layout.spinner);

        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将adapter 添加到spinner中
        spinner.setAdapter(adapter);
        //添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(spinnerSelectedListener);
        spinner.setSelection(0, true);
        //设置默认值
        spinner.setVisibility(View.VISIBLE);
    }

    private static class TypeSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            typeSelected.set(arg2);
            if (arg2 != 0) {
                buildQuery();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private static class RateSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            rateSelected.set(arg2);
            if (arg2 != 0) {
                buildQuery();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private static class MoneySpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            MoneySelected.set(arg2);
            if (arg2 != 0) {
                buildQuery();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }

    private static class TimeSpinnerSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                   long arg3) {
            TimeSelected.set(arg2);
            if (arg2 != 0) {
                buildQuery();
            }
        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }


    static void buildQuery() {
        String condition = "";
        if (typeSelected.intValue() == 3) {
            condition += "&type=%E8%B4%A7%E5%B8%81%E5%9F%BA%E9%87%91";
        }

        if (rateSelected.intValue() == 1) {
            condition += "&br=0&er=3";
        } else if (rateSelected.intValue() == 2) {
            condition += "&br=3&er=5";
        } else if (rateSelected.intValue() == 3) {
            condition += "&br=5&er=10";
        } else if (rateSelected.intValue() == 4) {
            condition += "&br=10&er=15";
        } else if (rateSelected.intValue() == 5) {
            condition += "&br=15&er=20";
        } else if (rateSelected.intValue() == 6) {
            condition += "&br=20";
        }

        if(MoneySelected.intValue()==1){
            condition += "&ba=0&ed=5";
        }else if(MoneySelected.intValue()==2){
            condition += "&ba=5&ed=10";
        }else if(MoneySelected.intValue()==3){
            condition += "&ba=10&ed=50";
        }else if(MoneySelected.intValue()==4){
            condition += "&ba=50";
        }

        if(TimeSelected.intValue()==1){
            condition += "&bic=0&eic=1";
        }else if(TimeSelected.intValue()==2){
            condition += "&bic=1&eic=3";
        }else if(TimeSelected.intValue()==3){
            condition += "&bic=3&eic=6";
        }else if(TimeSelected.intValue()==4){
            condition += "&bic=6&eic=12";
        }else if(TimeSelected.intValue()==5){
            condition += "&bic=12";
        }
        MainActivity.updateUi(condition);
    }
}
