package com.keddits.rx

import com.keddits.model.Child
import io.reactivex.Observable

/**
 * Created by marinaracu on 21.12.2017.
 */
class HelperRx {

    private lateinit var helperRxCallBack: HelperRxCallBack

    interface HelperRxCallBack {
        fun loadLastItems(childList: MutableList<Child>)
    }

    fun getLastItemList(initialList: MutableList<Child>, itemSkip: Long) {
        Observable.just(initialList)
                .flatMapIterable({ list -> list })
                .skip(itemSkip)
                .toList()
                .subscribe({ list -> helperRxCallBack.loadLastItems(list) })
    }

    fun setHelperRxCalback(helperRxCallBack: HelperRxCallBack) {
        this.helperRxCallBack = helperRxCallBack
    }

}