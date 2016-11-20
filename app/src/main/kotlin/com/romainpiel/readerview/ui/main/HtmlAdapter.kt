package com.romainpiel.readerview.ui.main

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.romainpiel.readerview.ui.ViewHolder
import com.romainpiel.readerview.ui.ViewModel

class HtmlAdapter: RecyclerView.Adapter<ViewHolder>() {
    object Type {
        val paragraph = 0
        val heading = 1
        val blockquote = 2
        val bullet = 3
    }

    val items: MutableList<ViewModel> = mutableListOf()

    fun setItems(items: List<ViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int) = items.get(position).type()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        onBindViewHolder(holder, position, null)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        holder?.bind(items[position], payloads)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            Type.paragraph -> ParagraphViewHolder(parent)
            Type.heading -> HeadingViewHolder(parent)
            Type.blockquote -> BlockquoteViewHolder(parent)
            Type.bullet -> BulletViewHolder(parent)
            else -> throw RuntimeException("Unknown type " + viewType)
        }
    }
}