package com.dd.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.whyalwaysmea.circular.AnimUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView click = (TextView) findViewById(R.id.Click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                AnimUtils.startIntent(intent,click, (Activity) MainActivity.this,R.id.ll_layoutss);
            }
        });

    }
}
