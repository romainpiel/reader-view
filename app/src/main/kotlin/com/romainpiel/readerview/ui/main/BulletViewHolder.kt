package com.romainpiel.readerview.ui.main

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BulletSpan
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.romainpiel.readerview.R
import com.romainpiel.readerview.ui.ViewHolder
import com.romainpiel.readerview.ui.ViewModel

class BulletViewHolder(parent: ViewGroup?): ViewHolder(R.layout.item_bullet, parent) {
    val textView: TextView by bindView(R.id.textView)

    override fun bind(viewModel: ViewModel, payloads: MutableList<Any>?) {
        val spannable = SpannableStringBuilder((viewModel as BulletViewModel).text)
        spannable.setSpan(BulletSpan(20), 0, spannable.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannable
    }
}