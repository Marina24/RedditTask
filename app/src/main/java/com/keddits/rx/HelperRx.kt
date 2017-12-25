package com.keddits.rx

import com.keddits.model.Child
import rx.Observable

/**
 * Created by marinaracu on 21.12.2017.
 */
class HelperRx {

    private lateinit var helperRxCallBack: HelperRxCallBack

    public interface HelperRxCallBack {
        fun loadLastItems(childList: MutableList<Child>)
    }

    public fun getLastItemList(initialList: MutableList<Child>, itemSkip: Int) {
        Observable.just(initialList)
                .flatMapIterable({ list -> list })
                .skip(itemSkip)
                .toList()
                .subscribe({list -> helperRxCallBack.loadLastItems(list)})
    }

    public fun setHelperRxCalback(helperRxCallBack: HelperRxCallBack) {
        this.helperRxCallBack = helperRxCallBack
    }

}