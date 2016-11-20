package com.romainpiel.readerview.repository.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @Provides
    fun okHttpClient() = OkHttpClient()
}