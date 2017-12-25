package com.keddits.ui.activity.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by marinaracu on 25.12.2017.
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayout() > 0) setContentView(getLayout())
    }

    abstract fun getLayout(): Int
}