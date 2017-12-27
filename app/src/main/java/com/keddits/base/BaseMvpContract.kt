package com.keddits.base

import android.content.Context
import android.support.annotation.StringRes

/**
 * Created by marinaracu on 26.12.2017.
 */
class BaseMvpContract {

    interface BasePresenter{
        fun onAttach(mvpView: BaseView)
        fun onDetach()
    }

    interface BaseView {

        fun getContext(): Context

        fun showProgress()

        fun hideProgress()

        fun showError(@StringRes strResId: Int)

        fun showError(error: String?)

        fun showMessage(@StringRes srtResId: Int)

        fun showMessage(message: String?)
    }
}