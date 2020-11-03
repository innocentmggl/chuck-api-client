package com.innocent.chuck.core.di

import android.content.Context
import com.innocent.chuck.core.service.AppScheduler
import com.innocent.chuck.core.service.IScheduler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun providesContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun scheduler(): IScheduler {
        return AppScheduler()
    }
}