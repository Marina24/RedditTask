package com.keddits.ui.listener

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

/**
 * Created by marinaracu on 22.12.2017.
 */
abstract class  PaginationScrollListener(var linearLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var visibleItemCount : Int = linearLayoutManager.childCount
        var totalItemCount : Int = linearLayoutManager.itemCount
        var firstVisibleItemPosition : Int = linearLayoutManager.findFirstVisibleItemPosition()


        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount <= getTotalPageCount()) {
                loadMoreItems()
            }
        }
    }


    abstract fun loadMoreItems()
    abstract fun getTotalPageCount() : Int
    abstract fun isLastPage() : Boolean
    abstract fun isLoading() : Boolean
}