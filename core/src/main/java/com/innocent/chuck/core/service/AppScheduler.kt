package com.innocent.chuck.core.service

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Implementation of [Scheduler] with actual threads.
 * */

class AppScheduler: IScheduler {

    override fun mainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}