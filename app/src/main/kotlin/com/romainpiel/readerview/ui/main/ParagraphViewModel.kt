package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.ui.ViewModel

class ParagraphViewModel(val text: String): ViewModel {
    override fun type() = HtmlAdapter.Type.paragraph
}