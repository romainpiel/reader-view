package com.romainpiel.readerview.ui.main

import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.romainpiel.readerview.R
import com.romainpiel.readerview.ui.ViewHolder
import com.romainpiel.readerview.ui.ViewModel

class ParagraphViewHolder(parent: ViewGroup?): ViewHolder(R.layout.item_paragraph, parent) {
    val textView: TextView by bindView(R.id.textView)

    override fun bind(viewModel: ViewModel, payloads: MutableList<Any>?) {
        textView.text = (viewModel as ParagraphViewModel).text
    }
}