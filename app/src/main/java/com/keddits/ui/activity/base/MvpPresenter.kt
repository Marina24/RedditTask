package com.keddits.ui.activity.base

/**
 * Created by marinaracu on 25.12.2017.
 */
interface MvpPresenter<V : MvpView> {
    fun onAttach(mvpView: V)
    fun onDetach()
    fun getView() : V
}