package com.romainpiel.readerview

import android.app.Application
import com.romainpiel.readerview.dagger.ApplicationModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class ReaderViewApp : Application() {

    lateinit var component: ApplicationComponent
        get

    override fun onCreate() {
        super.onCreate()

        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}