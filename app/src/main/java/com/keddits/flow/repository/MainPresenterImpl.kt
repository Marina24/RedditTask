package com.keddits.flow.repository

import android.util.Log
import com.keddits.base.BasePresenterImpl
import com.keddits.data.source.repository.ReposRepository
import com.keddits.model.Child
import com.keddits.rx.HelperRx
import com.keddits.flow.repository.MainPresenterContract.MainView

/**
 * Created by marinaracu on 25.12.2017.
 */
class MainPresenterImpl(var dataCountLimit: Int) : BasePresenterImpl<MainView>(), MainPresenterContract.MainPresenter,
        HelperRx.HelperRxCallBack {

    private var helperRx: HelperRx = HelperRx()
    private val repository: ReposRepository = ReposRepository()

    override fun initRepositories() {
        view?.showProgress()
        fetchRepositories(dataCountLimit, true)
    }


    override fun fetchRepositories() {
        view?.showProgress()
        fetchRepositories(dataCountLimit, false)
    }

    private fun fetchRepositories(dataCountLimit: Int, local: Boolean) {
        addDisposable(repository.getRepositories(dataCountLimit, local).subscribe({ gameData ->
            view?.hideProgress()
            view?.displayGameData(gameData)
        }, { throwable ->
            view?.hideProgress()
            view?.showError(throwable.message)
        }))
    }

    override fun onItemNewsClicked(url: String) {
        view?.openDetailGame(url)
    }

    override fun loadLastItems(childList: MutableList<Child>) {
        view?.showLastItemLists(childList)
    }

    override fun loadNextData(dataCountLimit: Int, exitingItems: Int) {
        fetchRepositories(dataCountLimit, false)
    }

    override fun loadLastData(initialList: MutableList<Child>, skipCount: Long) {
        helperRx.setHelperRxCalback(this)
        helperRx.getLastItemList(initialList, skipCount)
    }
}