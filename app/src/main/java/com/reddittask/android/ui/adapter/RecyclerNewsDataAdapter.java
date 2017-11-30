package com.reddittask.android.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.reddittask.android.R;
import com.reddittask.android.model.Child;
import com.reddittask.android.model.DataInside;
import com.reddittask.android.model.GameData;
import com.reddittask.android.ui.activity.main.MainPresenter;
import com.reddittask.android.util.DateUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by marinaracu on 30.11.2017.
 */

public class RecyclerNewsDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private GameData mNewsData;
    private Context mContext;
    private MainPresenter mMainPresenter;
    private boolean mIsLoadingAdded = false;

    public RecyclerNewsDataAdapter(GameData newsData, Context context, MainPresenter mainPresenter) {
        mNewsData = newsData;
        mContext = context;
        mMainPresenter = mainPresenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                viewHolder = getViewHolder(parent, inflater);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.item_progress, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View view = inflater.inflate(R.layout.item_game, parent, false);
        viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        DataInside dataInside = mNewsData.getData().getChildren().get(position).getData();

        switch (getItemViewType(position)) {
            case ITEM:
                GameViewHolder gameViewHolder = (GameViewHolder) holder;
                String textPostDate = String.format("%s",
                        DateUtil.convertTime(dataInside.getCreatedUtc())) + R.string.text_hour_ago;
                String textRating = "Score: " + Integer.toString(dataInside.getScore());
                String textCountComment = Integer.toString(dataInside.getNumComments()) + " comments";
                if (!TextUtils.isEmpty(dataInside.getThumbnail()))
                    Picasso.with(mContext).load(dataInside.getThumbnail())
                            .into(gameViewHolder.mImageNews, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {
                                    gameViewHolder.mImageNews.setImageResource(R.mipmap.ic_launcher);
                                }
                            });
                gameViewHolder.mTextNewsTitle.setText(dataInside.getTitle());
                gameViewHolder.mTextAuthor.setText(dataInside.getAuthor());
                gameViewHolder.mTextSubreddit.setText(dataInside.getSubreddit());
                gameViewHolder.mTextPostDate.setText(textPostDate);
                gameViewHolder.mTextRating.setText(textRating);
                gameViewHolder.mTextCommentCount.setText(textCountComment);
                gameViewHolder.mLayoutItem.setOnClickListener(view -> {
                    mMainPresenter.onItemNewsClicked(position, dataInside.getUrl());
                });
                break;
            case LOADING:
//                Do nothing
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mNewsData == null ? 0 : mNewsData.getData().getChildren().size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mNewsData.getData().getChildren().size() - 1 && mIsLoadingAdded) ? LOADING : ITEM;
    }

    public void add(Child child) {
        mNewsData.getData().getChildren().add(child);
        notifyItemInserted(mNewsData.getData().getChildren().size() - 1);
    }

    public void addAll(List<Child> childList) {
        for (Child child : childList) {
            add(child);
        }
    }

    public void addLoadingFooter() {
        mIsLoadingAdded = true;
        add(new Child());
    }

    public void removeLoadingFooter() {
        mIsLoadingAdded = false;

        int position = mNewsData.getData().getChildren().size() - 1;
        Child item = getItem(position);

        if (item != null) {
            mNewsData.getData().getChildren().remove(position);
            notifyItemRemoved(position);
        }
    }

    public Child getItem(int position) {
        return mNewsData.getData().getChildren().get(position);
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_item)
        protected RelativeLayout mLayoutItem;
        @BindView(R.id.img_news)
        protected ImageView mImageNews;
        @BindView(R.id.txt_name_news)
        protected TextView mTextNewsTitle;
        @BindView(R.id.txt_author_name)
        protected TextView mTextAuthor;
        @BindView(R.id.txt_subreddit)
        protected TextView mTextSubreddit;
        @BindView(R.id.txt_post_date)
        protected TextView mTextPostDate;
        @BindView(R.id.txt_rating)
        protected TextView mTextRating;
        @BindView(R.id.txt_comment_count)
        protected TextView mTextCommentCount;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    protected class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }

}
