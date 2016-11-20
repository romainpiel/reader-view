package com.romainpiel.readerview.dagger

import android.content.Context
import com.romainpiel.readerview.ReaderViewApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ReaderViewApp) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }
}
