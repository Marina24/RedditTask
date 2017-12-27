package com.keddits.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by marinaracu on 26.12.2017.
 */
abstract class BasePresenterImpl<V : BaseMvpContract.BaseView> : BaseMvpContract.BasePresenter {

    protected var view : V? = null
    private val compositeDisposibal : CompositeDisposable = CompositeDisposable()

    /**
     * Attach view to presenter, also here we have subscription
     * for destroy event. On destroy event we should detach view
     * and destroy presenter

     * @param view extend BaseView
     */
    @Suppress("UNCHECKED_CAST")
    override fun onAttach(mvpView: BaseMvpContract.BaseView) {
        this.view = mvpView as V
    }

    /**
     * Here we are detaching view and removing and
     * unsubscribing all subscriptions
     */
    override fun onDetach() {
       compositeDisposibal.dispose()
        view = null
    }

    /**
     * This method adds given rx subscription to the [.mSubscriptionList]
     * which is unsubscribed [.detachView]

     * @param subscription - rx subscription that must be unsubscribed [.detachView]
     */
    protected fun addDisposable(disposable: Disposable){
        compositeDisposibal.add(disposable)
    }
}