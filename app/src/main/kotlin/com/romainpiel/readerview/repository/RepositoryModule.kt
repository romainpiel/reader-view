package com.romainpiel.readerview.repository

import com.romainpiel.readerview.repository.network.NetworkModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class))
class RepositoryModule {
    @Provides
    @Singleton
    fun htmlRepository(okHttpClient: OkHttpClient) = HtmlRepository(okHttpClient)
}