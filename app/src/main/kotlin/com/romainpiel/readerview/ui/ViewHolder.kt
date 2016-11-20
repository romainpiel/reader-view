package com.romainpiel.readerview.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class ViewHolder : RecyclerView.ViewHolder {
    constructor(layoutResId: Int, parent: ViewGroup?) :
    super(LayoutInflater.from(parent?.context).inflate(layoutResId, parent, false))

    abstract fun bind(viewModel: ViewModel, payloads: MutableList<Any>? = null)
}
