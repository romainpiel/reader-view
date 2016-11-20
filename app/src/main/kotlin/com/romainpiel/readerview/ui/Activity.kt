package com.romainpiel.readerview.ui

import android.app.Activity
import com.romainpiel.readerview.ApplicationComponent
import com.romainpiel.readerview.ReaderViewApp

val Activity.applicationComponent: ApplicationComponent
    get() = (application as ReaderViewApp).component