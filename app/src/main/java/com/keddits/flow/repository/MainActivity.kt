package com.keddits.flow.repository

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import com.keddits.Const
import com.keddits.R
import com.keddits.model.Child
import com.keddits.base.BaseActivity
import com.keddits.flow.repository.MainPresenterContract.MainPresenter
import com.keddits.model.GameData
import com.keddits.ui.listener.PaginationScrollListener
import com.keddits.util.UiUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by marinaracu on 21.12.2017.
 */
class MainActivity : BaseActivity<MainPresenter>(), MainPresenterContract.MainView {
    override val layoutResourceId: Int = R.layout.activity_main
    override val presenter: MainPresenterImpl = MainPresenterImpl(Const.PAGE_START)

    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var currentPage: Int = Const.PAGE_START
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: RecyclerNewsDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        presenter.initRepositories()
        addLoadMoreListener()
    }

    private fun init() {
        linearLayoutManager = LinearLayoutManager(this)
        recycler_movie.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerNewsDataAdapter { presenter.onItemNewsClicked(it.data!!.url) }
        recycler_movie.adapter = recyclerAdapter
    }

    private fun addLoadMoreListener() {
        recycler_movie.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 10
                presenter.loadNextData(currentPage, recyclerAdapter.itemCount)
            }

            override fun getTotalPageCount(): Int {
                return Const.TOTAL_PAGES
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

        })
    }

    override fun openDetailGame(url: String) {
        // Build the custom tab intent and launch the url
        val customTabIntent: CustomTabsIntent = UiUtil.buildCustomTabsIntent(this)
        customTabIntent.launchUrl(getContext(), Uri.parse(url))
    }

    override fun displayGameData(gameData: GameData) {
        if (recyclerAdapter.itemCount == 0) {
            recyclerAdapter.setGameData(gameData.data.children)
        } else {
            recyclerAdapter.removeLoadingFooter()
        }
        presenter.loadLastData(gameData.data.children, recyclerAdapter.itemCount.toLong())
    }

    override fun showErrorMessage() {

    }

    override fun showLastItemLists(cildren: MutableList<Child>) {
        isLoading = false
        recyclerAdapter.addAll(cildren)

        if (currentPage != Const.TOTAL_PAGES) recyclerAdapter.addLoadingFooter()
        else isLastPage = true
    }

    override fun getContext(): Context {
        return this@MainActivity
    }

}