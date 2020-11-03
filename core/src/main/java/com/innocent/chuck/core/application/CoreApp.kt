package com.innocent.chuck.core.application

import android.app.Application
import com.innocent.chuck.core.di.AppModule
import com.innocent.chuck.core.di.CoreComponent
import com.innocent.chuck.core.di.DaggerCoreComponent

open class CoreApp : Application() {

    companion object {
        lateinit var coreComponent: CoreComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
       coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
    }
}