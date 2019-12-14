package com.roomapp.toolsloan.ui.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.entity.Friend
import kotlinx.android.synthetic.main.item_friend.view.*
import kotlinx.android.synthetic.main.item_friend.view.imageView
import java.util.*
import android.graphics.Color


class FriendItemAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<FriendItemAdapter.FriendItemViewHolder>() {

    private var data: List<Friend> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendItemViewHolder {
        return FriendItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_friend, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FriendItemViewHolder, position: Int) =
        holder.bind(data[position],itemClickListener)

    fun swapData(data: List<Friend>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FriendItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(friend: Friend,clickListener: OnItemClickListener) = with(itemView) {
            txtFriendName.text = friend.friendName

            val drawable = TextDrawable.builder()
                .buildRect(friend.friendName.get(0).toString(), Color.RED)

            itemView.imageView.setImageDrawable(drawable)

            itemView.setOnClickListener{
                clickListener.onItemClicked(friend)
            }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(friend: Friend)
}