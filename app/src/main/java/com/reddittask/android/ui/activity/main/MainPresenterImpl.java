package com.reddittask.android.ui.activity.main;

import com.reddittask.android.api.Queries;
import com.reddittask.android.model.GameData;
import com.reddittask.android.ui.activity.base.MvpView;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class MainPresenterImpl implements MainPresenter, Queries.CallBack {

    private MainView mMainView;
    private Queries mQueries;


    public MainPresenterImpl(MainView mainView, Queries queries, int dataCountLimit) {
        mMainView = mainView;
        mQueries = queries;
        mQueries.getAllDataMovies(dataCountLimit);
        mQueries.setCallBack(this);
    }


    @Override
    public void onAttach(MvpView mvpView) {
        mvpView.showLoading();
    }

    @Override
    public void onDetach() {
        mQueries.onStop();
    }

    @Override
    public void onItemNewsClicked(int position, String url) {
        mMainView.openDetailGame(position, url);
    }

    @Override
    public void loadNextData(int dataCountLimit) {
        mQueries.getAllDataMovies(dataCountLimit);
    }


    @Override
    public void onError(Throwable throwable) {
        mMainView.showErrorMessage();
        mMainView.hideLoading();
    }

    @Override
    public void onCompleted(GameData gameData) {
        mMainView.displayGameData(gameData);
    }

    @Override
    public void onFinish() {
        mMainView.hideLoading();
    }
}
