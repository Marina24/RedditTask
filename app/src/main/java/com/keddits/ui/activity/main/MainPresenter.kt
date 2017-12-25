package com.keddits.ui.activity.main

import com.keddits.model.Child
import com.keddits.ui.activity.base.MvpPresenter

/**
 * Created by marinaracu on 25.12.2017.
 */
interface MainPresenter<V : MainView> : MvpPresenter<V> {

    fun onItemNewsClicked(url: String)

    fun loadNextData(dataCountLimit: Int, exitingItems: Int)

    fun loadLastData(initialList: MutableList<Child>, skipCount: Int)
}