package com.keddits.ui.activity.main

import com.keddits.model.Child
import com.keddits.model.GameData
import com.keddits.ui.activity.base.MvpView

/**
 * Created by marinaracu on 25.12.2017.
 */
interface MainView : MvpView {

    fun openDetailGame(url : String)

    fun displayGameData(gameData : MutableList<Child>)

    fun showErrorMessage()

    fun showLastItemLists(cildren : MutableList<Child>)
}