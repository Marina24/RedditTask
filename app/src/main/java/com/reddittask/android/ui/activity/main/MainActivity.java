package com.reddittask.android.ui.activity.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.reddittask.android.R;
import com.reddittask.android.api.Queries;
import com.reddittask.android.model.Child;
import com.reddittask.android.model.GameData;
import com.reddittask.android.ui.activity.base.BaseActivity;
import com.reddittask.android.ui.adapter.RecyclerNewsDataAdapter;
import com.reddittask.android.ui.dialog.MaterialDialog;
import com.reddittask.android.ui.listener.PaginationScrollListener;
import com.reddittask.android.util.CommonUtil;
import com.reddittask.android.util.UiUtil;

import java.util.List;

import butterknife.BindView;

import static com.reddittask.android.Const.PAGE_START;
import static com.reddittask.android.Const.TOTAL_PAGES;

public class MainActivity extends BaseActivity implements MainView {

    @BindView(R.id.recycler_movie)
    protected RecyclerView mRecyclerViewNews;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerNewsDataAdapter mNewsDataAdapter;
    private MaterialDialog mProgressBar;
    private MainPresenterImpl mMainPresenter;
    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mCurrentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainPresenter = new MainPresenterImpl(this, new Queries(), CommonUtil.convertStringToInt(PAGE_START));

        init();
        mMainPresenter.onAttach(this);
        addLoadMoreListener();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    private void init() {
        mProgressBar = new MaterialDialog(this);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewNews.setLayoutManager(mLinearLayoutManager);
    }

    private void addLoadMoreListener() {
        mRecyclerViewNews.addOnScrollListener(new PaginationScrollListener(mLinearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                mIsLoading = true;
                mCurrentPage += 1;
                mMainPresenter.loadNextData(CommonUtil.convertStringToInt(mCurrentPage));
            }


            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return mIsLastPage;
            }

            @Override
            public boolean isLoading() {
                return mIsLoading;
            }
        });
    }

    @Override
    public void showLoading() {
        mProgressBar.show();
    }

    @Override
    public void hideLoading() {
        mProgressBar.hide();
    }

    @Override
    public void openDetailGame(int position, String url) {
        // Build the custom tab intent and launch the url
        CustomTabsIntent customTabsIntent = UiUtil.buildCustomTabsIntent(this);
        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));
    }

    @Override
    public void displayGameData(GameData gameData) {
        if (mNewsDataAdapter == null) {
            mNewsDataAdapter = new RecyclerNewsDataAdapter(gameData, this, mMainPresenter);
            mRecyclerViewNews.setAdapter(mNewsDataAdapter);
        } else {
            mNewsDataAdapter.removeLoadingFooter();
        }
        List<Child> gameDataChildren = getLastGames(mNewsDataAdapter.getItemCount(), gameData.getData().getChildren());
        mIsLoading = false;

        mNewsDataAdapter.addAll(gameDataChildren);

        if (mCurrentPage != TOTAL_PAGES) mNewsDataAdapter.addLoadingFooter();
        else mIsLastPage = true;
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, getResources().getString(R.string.error_msg), Toast.LENGTH_SHORT).show();
    }

    public static List<Child> getLastGames(int itemCount, List<Child> allListData) {
        return allListData.subList(itemCount, allListData.size());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.onDetach();
    }
}
