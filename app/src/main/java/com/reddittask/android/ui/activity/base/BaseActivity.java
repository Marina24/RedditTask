package com.reddittask.android.ui.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by marinaracu on 30.11.2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayout() > 0) {
            setContentView(getLayout());
            ButterKnife.bind(this);
        }
    }


    public abstract int getLayout();
}
