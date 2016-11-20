package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.ui.ViewModel

class BulletViewModel(val text: CharSequence): ViewModel {
    override fun type() = HtmlAdapter.Type.bullet
}