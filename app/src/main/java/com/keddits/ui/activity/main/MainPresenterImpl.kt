package com.keddits.ui.activity.main

import com.keddits.api.Queries
import com.keddits.model.Child
import com.keddits.model.GameData
import com.keddits.rx.HelperRx

/**
 * Created by marinaracu on 25.12.2017.
 */
class MainPresenterImpl(var queries: Queries, var dataCountLimit: Int) : MainPresenter<MainView>, Queries.CallBack, HelperRx.HelperRxCallBack {
    private lateinit var  mainView : MainView
    private lateinit var helperRx : HelperRx

    override fun onAttach(mvpView: MainView) {
        mainView = mvpView
        mainView.showLoadding()
        queries.getAllDatamovie(dataCountLimit)
        queries.setCallBack(this)
        helperRx = HelperRx()
        helperRx.setHelperRxCalback(this)
    }

    override fun onDetach() {
        queries.onStop()
    }

    override fun getView(): MainView {
        return mainView
    }

    override fun onItemNewsClicked(url: String) {
        mainView.openDetailGame(url)
    }

    override fun loadLastItems(childList: MutableList<Child>) {
        mainView.showLastItemLists(childList)
    }

    override fun loadNextData(dataCountLimit: Int, exitingItems: Int) {
        queries.getAllDatamovie(dataCountLimit)
    }

    override fun loadLastData(initialList: MutableList<Child>, skipCount: Int) {
        helperRx.getLastItemList(initialList, skipCount)
    }

    override fun onError(throwable: Throwable) {
        mainView.showErrorMessage()
        mainView.hideLoadding()
    }

    override fun onCompleted(gameData: GameData) {
        mainView.displayGameData(gameData.data.children)
    }

    override fun onFinish() {
        mainView.hideLoadding()
    }
}