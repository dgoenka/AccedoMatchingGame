package com.divyanshgoenka.accedomatchinggame.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by divyanshgoenka on 11/06/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setup(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        register();
    }

    protected void setup(Bundle savedInstanceState){}

    protected void register(){}

    protected void unregister(){}


}
