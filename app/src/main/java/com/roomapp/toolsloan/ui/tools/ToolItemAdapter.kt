package com.roomapp.toolsloan.ui.tools

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roomapp.toolsloan.R
import com.roomapp.toolsloan.entity.Tool
import com.hah.forecastmvvm.internal.glide.GlideApp
import kotlinx.android.synthetic.main.item_tool.view.*
import java.util.*

class ToolItemAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ToolItemAdapter.ToolItemViewHolder>() {

    private var data: List<Tool> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolItemViewHolder {
        return ToolItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tool, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ToolItemViewHolder, position: Int) =
        holder.bind(data[position],itemClickListener)

    fun swapData(data: List<Tool>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ToolItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tool: Tool,clickListener: OnItemClickListener) = with(itemView) {
            txtToolName.text = tool.toolName
            txtTotalCount.text = String.format(resources.getString(R.string.str_total), tool.totalCount)
            txtAvailableItem.text =  String.format(resources.getString(R.string.str_borrowed), tool.count)

            GlideApp.with(itemView.context)
                .load(tool.drawable)
                .into(itemView.imageView)

            itemView.setOnClickListener {
                clickListener.onItemClicked(tool)
            }
        }
    }
}


interface OnItemClickListener{
    fun onItemClicked(tool: Tool)
}