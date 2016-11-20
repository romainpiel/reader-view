package com.romainpiel.readerview

import com.romainpiel.readerview.dagger.ApplicationModule
import com.romainpiel.readerview.repository.HtmlRepository
import com.romainpiel.readerview.repository.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        RepositoryModule::class
))
interface ApplicationComponent {
    fun inject(application: ReaderViewApp)

    fun htmlRepository(): HtmlRepository
}