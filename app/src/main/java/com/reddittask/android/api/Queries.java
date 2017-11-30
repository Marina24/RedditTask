package com.reddittask.android.api;

import com.reddittask.android.model.GameData;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class Queries {

    private CompositeSubscription mCompositeSubscription;
    private CallBack mCallBack;

    public interface CallBack {
        void onError(Throwable throwable);

        void onCompleted(GameData dataMovies);

        void onFinish();
    }

    public void getAllDataMovies(int limit) {
        mCompositeSubscription = new CompositeSubscription();

        ApiService apiService = ApiManager.getClient().create(ApiService.class);

        Subscription subscription = apiService.getMoviesData(limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<GameData>() {
                    @Override
                    public void onCompleted() {
                        if (mCallBack != null) {
                            mCallBack.onFinish();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (mCallBack != null) {
                            mCallBack.onError(e);
                        }
                    }

                    @Override
                    public void onNext(GameData dataResponse) {
                        if (mCallBack != null){
                            mCallBack.onCompleted(dataResponse);
                        }
                    }
                });

        mCompositeSubscription.add(subscription);
    }

    public void onStop() {
        if (mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
        mCallBack = null;
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }
}
