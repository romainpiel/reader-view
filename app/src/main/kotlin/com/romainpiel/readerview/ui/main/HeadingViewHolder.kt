package com.romainpiel.readerview.ui.main

import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.romainpiel.readerview.R
import com.romainpiel.readerview.ui.ViewHolder
import com.romainpiel.readerview.ui.ViewModel

class HeadingViewHolder(parent: ViewGroup?): ViewHolder(R.layout.item_heading, parent) {
    val textView: TextView by bindView(R.id.textView)

    override fun bind(viewModel: ViewModel, payloads: MutableList<Any>?) {
        if (viewModel !is HeadingViewModel) return
        textView.text = viewModel.text
        textView.setTextAppearance(itemView.context, viewModel.textAppearance)
    }
}