package com.divyanshgoenka.accedomatchinggame.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.divyanshgoenka.accedomatchinggame.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This abstract class overrides methods and uses
 * <p>
 * <p>
 * <p>
 * Created by divyanshgoenka on 11/06/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar mToolBar;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (toolbar != null)
            setSupportActionBar(toolbar);
        setup(savedInstanceState);
    }

    @Nullable
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;

    protected TextView getActionBarTextView() {
        return getActionBarTextView(toolbar);
    }

    private TextView getActionBarTextView(ViewGroup toolbar) {
        TextView toolbarTitle = null;
        for (int i = 0; i < toolbar.getChildCount(); ++i) {
            View child = toolbar.getChildAt(i);

            // assuming that the title is the first instance of TextView
            // you can also check if the title string matches
            if (child instanceof TextView) {
                toolbarTitle = (TextView) child;
                break;
            } else if (child instanceof ViewGroup)
                return getActionBarTextView((ViewGroup) child);
        }
        return toolbarTitle;
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

    protected void setup(Bundle savedInstanceState) {
    }

    protected void register() {
    }

    protected void unregister() {
    }


}
