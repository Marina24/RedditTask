package com.keddits.flow.repository

import com.keddits.base.BaseMvpContract
import com.keddits.model.Child
import com.keddits.model.GameData

/**
 * Created by marinaracu on 26.12.2017.
 */
class MainPresenterContract {

    interface MainPresenter: BaseMvpContract.BasePresenter {

        fun onItemNewsClicked(url: String)

        fun loadNextData(dataCountLimit: Int, exitingItems: Int)

        fun loadLastData(initialList: MutableList<Child>, skipCount: Long)

        fun initRepositories()

        fun fetchRepositories()
    }

    interface MainView : BaseMvpContract.BaseView {

        fun openDetailGame(url : String)

        fun displayGameData(gameData: GameData)

        fun showErrorMessage()

        fun showLastItemLists(cildren : MutableList<Child>)
    }
}