package com.romainpiel.readerview.dagger

import android.app.Activity
import com.romainpiel.readerview.ApplicationComponent
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun provideActivity() : Activity
}