package com.roomapp.toolsloan.ui.friend_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.entity.LoanWithFriendAndTool
import com.hah.forecastmvvm.internal.glide.GlideApp
import kotlinx.android.synthetic.main.item_tool.view.*
import java.util.*

class ToolsByFriendAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ToolsByFriendAdapter.ToolsByFriendViewHolder>() {

    private var data: List<LoanWithFriendAndTool> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsByFriendViewHolder {
        return ToolsByFriendViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tool_friend, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ToolsByFriendViewHolder, position: Int) =
        holder.bind(data[position],itemClickListener)

    fun swapData(data: List<LoanWithFriendAndTool>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ToolsByFriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tool: LoanWithFriendAndTool,clickListener: OnItemClickListener) = with(itemView) {
            txtToolName.text = tool.toolName
            txtTotalCount.text = tool.count.toString()

            GlideApp.with(itemView.context)
                .load(tool.image)
                .into(itemView.imageView)

            itemView.setOnClickListener{
                clickListener.onItemClicked(tool)
            }
        }
    }
}


interface OnItemClickListener{
    fun onItemClicked(loanwithFriendAndTool: LoanWithFriendAndTool)
}