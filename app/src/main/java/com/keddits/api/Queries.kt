package com.keddits.api

import com.keddits.model.GameData
import rx.Scheduler
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription

/**
 * Created by marinaracu on 21.12.2017.
 */
class Queries {
    private lateinit var compositSubscriton: CompositeSubscription
    private lateinit var callBack: CallBack

    public interface CallBack {
        fun onError(throwable: Throwable)
        fun onCompleted(gameData: GameData)
        fun onFinish()
    }

    fun getAllDatamovie(limit: Int) {
        compositSubscriton = CompositeSubscription()
        val apiService: ApiService = ApiManager.getClient().create(ApiService::class.java)

        val subscription: Subscription = apiService.getGameData(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ gameData -> callBack.onCompleted(gameData) },
                        { error -> callBack.onError(error) },
                        { callBack.onFinish() })

        compositSubscriton.add(subscription)

    }

    fun onStop() {
        if (compositSubscriton.isUnsubscribed) compositSubscriton.unsubscribe()
    }

    fun setCallBack(callBack: CallBack) {
        this.callBack = callBack
    }
}