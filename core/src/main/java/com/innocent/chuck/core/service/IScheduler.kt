package com.innocent.chuck.core.service

import io.reactivex.Scheduler

interface IScheduler {
    fun mainThread(): Scheduler
    fun io():Scheduler
}