package com.romainpiel.readerview.repository

import com.romainpiel.readerview.repository.network.HttpException
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.Observable
import java.io.IOException

class HtmlRepository(val okHttpClient: OkHttpClient) {
    fun get(url: String): Observable<Document> {
        return Observable.defer({
            try {
                val request = Request.Builder().url(url).build()
                Observable.just(okHttpClient.newCall(request).execute())
            } catch (e: IOException) {
                Observable.error<Response>(e)
            }
        }).map {
            if (it.isSuccessful) {
                Jsoup.parse(it.body().byteStream(), null, it.request().url().toString())
            } else {
                throw HttpException()
            }
        }
    }
}