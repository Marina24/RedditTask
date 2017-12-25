package com.keddits.ui.adapter

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.keddits.R
import com.keddits.model.Child
import com.keddits.util.DateUtil
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_game.view.*

/**
 * Created by marinaracu on 21.12.2017.
 */
class RecyclerNewsDataAdapter(val onClick: (Child) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM = 0
        const val LOADING = 1
    }

    var childList: MutableList<Child> = mutableListOf()
    var isLoadingAdded: Boolean = false

    fun setGameData(childList: MutableList<Child>) {
        this.childList = childList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (childList.isEmpty()) {
            0
        } else {
            childList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val dataInside = childList[position]
        when (getItemViewType(position)) {
            ITEM -> {
                var gameViewHolder = holder as GameViewHolder
                gameViewHolder.bindData(dataInside)
            }
            LOADING -> {
            }//Do nothing
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        var viewHolder: RecyclerView.ViewHolder? = null
        var inflater: LayoutInflater = LayoutInflater.from(parent?.context)
        when (viewType) {
            ITEM -> {
                var binding: View = inflater.inflate(R.layout.item_game, parent, false)
                viewHolder = GameViewHolder(binding, onClick)
            }
            LOADING -> {
                var viewLoadding: View = inflater.inflate(R.layout.item_progress, parent, false)
                viewHolder = LoadingViewHolder(viewLoadding)
            }
        }

        return viewHolder
    }

    fun add(child: Child) {
        childList.add(child)
        notifyItemInserted(childList.size - 1)
    }

    fun addAll(childList: MutableList<Child>) {
        for (child: Child in childList) {
            add(child)
        }
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        add(Child())
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = childList.size - 1

        val child = getItem(position)

        childList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == childList.size - 1 && isLoadingAdded) {
            LOADING
        } else {
            ITEM
        }
    }

    fun getItem(position: Int): Child {
        return childList[position]
    }

    class GameViewHolder(val binding: View?, val onClick: (Child) -> Unit) : RecyclerView.ViewHolder(binding) {

        fun bindData(child: Child) {
            with(child) {
                if (!TextUtils.isEmpty(data?.thumbnail))
                    Picasso.with(binding?.context)
                            .load(data?.thumbnail)
                            .into(binding?.img_news, object : Callback {
                                override fun onSuccess() {

                                }

                                override fun onError() {
                                    binding?.img_news?.setImageResource(R.mipmap.ic_launcher)
                                }

                            })
                binding?.txt_name_news?.text = data?.title
                binding?.txt_author_name?.text = data?.author
                binding?.txt_subreddit?.text = data?.subreddit
                if (data?.createdUtc != null)
                    binding?.txt_post_date?.text = DateUtil.convertTime(data!!.createdUtc) + R.string.text_hour_ago
                binding?.txt_rating?.text = "Score: " + data?.score.toString()
                binding?.txt_comment_count?.text = data?.numComments.toString() + " comments"
                binding?.setOnClickListener { onClick(this) }
            }
        }
    }

    class LoadingViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}