package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.ui.ViewModel

class BlockquoteViewModel(val text: String): ViewModel {
    override fun type() = HtmlAdapter.Type.blockquote
}