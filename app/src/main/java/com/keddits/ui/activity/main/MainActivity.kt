package com.keddits.ui.activity.main

import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.keddits.Const
import com.keddits.R
import com.keddits.api.Queries
import com.keddits.model.Child
import com.keddits.ui.activity.base.BaseActivity
import com.keddits.ui.adapter.RecyclerNewsDataAdapter
import com.keddits.ui.dialog.MaterialDialog
import com.keddits.ui.listener.PaginationScrollListener
import com.keddits.util.UiUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * Created by marinaracu on 21.12.2017.
 */
class MainActivity : BaseActivity(), MainView {

    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var currentPage: Int = Const.PAGE_START
    private lateinit var mainPresenter: MainPresenterImpl
    private lateinit var matiralProgress: MaterialDialog
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: RecyclerNewsDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainPresenter = MainPresenterImpl(Queries(), Const.PAGE_START)
        init()
        mainPresenter.onAttach(this)
        addLoadMoreListener()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun init() {
        matiralProgress = MaterialDialog(this)
        linearLayoutManager = LinearLayoutManager(this)
        recycler_movie.layoutManager = linearLayoutManager
        recyclerAdapter = RecyclerNewsDataAdapter { mainPresenter.onItemNewsClicked(it.data!!.url) }
        recycler_movie.adapter = recyclerAdapter
    }

    private fun addLoadMoreListener() {
        recycler_movie.addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
            override fun loadMoreItems() {
                isLoading = true
                currentPage += 10
                mainPresenter.loadNextData(currentPage, recyclerAdapter.itemCount)
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

    override fun showLoadding() {
        matiralProgress.show()
    }

    override fun hideLoadding() {
        matiralProgress.hide()
    }

    override fun openDetailGame(url: String) {
        // Build the custom tab intent and launch the url
        val customTabIntent: CustomTabsIntent = UiUtil.buildCustomTabsIntent(this)
        customTabIntent.launchUrl(this, Uri.parse(url))
    }

    override fun displayGameData(gameData: MutableList<Child>) {
        Log.d("DebugH-0",  currentPage.toString())
        if (recyclerAdapter.itemCount == 0) {
            recyclerAdapter.setGameData(gameData)
        } else {
            recyclerAdapter.removeLoadingFooter()
        }
        Log.d("DebugH-1",  currentPage.toString())
        mainPresenter.loadLastData(gameData, recyclerAdapter.itemCount)
    }

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show()
    }

    override fun showLastItemLists(cildren: MutableList<Child>) {
        isLoading = false
        recyclerAdapter.addAll(cildren)

        if (currentPage != Const.TOTAL_PAGES) recyclerAdapter.addLoadingFooter()
        else isLastPage = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.onDetach()
    }
}