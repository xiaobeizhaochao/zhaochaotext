package com.dd.myapplication;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.whyalwaysmea.circular.AnimUtils;

public class TestActivity extends AppCompatActivity {
    private View ll_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        AnimUtils.animhpel((Activity) this,R.id.ll_layout);
    }
    @Override
    public void onBackPressed() {
        AnimUtils.finishonBackPressed(TestActivity.this,R.id.ll_layoutss);
    }
}
