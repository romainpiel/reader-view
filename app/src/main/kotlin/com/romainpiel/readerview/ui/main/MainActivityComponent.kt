package com.romainpiel.readerview.ui.main

import com.romainpiel.readerview.ApplicationComponent
import com.romainpiel.readerview.dagger.ActivityComponent
import com.romainpiel.readerview.dagger.ActivityModule
import com.romainpiel.readerview.dagger.PerActivity
import dagger.Component

@PerActivity
@Component(
        dependencies = arrayOf(ApplicationComponent::class),
        modules = arrayOf(ActivityModule::class))
interface MainActivityComponent : ActivityComponent {
    fun inject(activity: MainActivity)
}
