package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.R
import com.romainpiel.readerview.ui.ViewModel

class HeadingViewModel(val level: Int, val text: String): ViewModel {
    val textAppearance: Int

    init {
        textAppearance = when(level) {
            1 -> R.style.TextAppearance_ReaderView_Headline
            2 -> R.style.TextAppearance_ReaderView_Title
            3, 4, 5, 6 -> R.style.TextAppearance_ReaderView_Subhead
            else -> throw IllegalStateException()
        }
    }

    override fun type() = HtmlAdapter.Type.heading
}