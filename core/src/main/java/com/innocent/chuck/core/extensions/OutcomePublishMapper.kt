package com.innocent.chuck.core.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innocent.chuck.core.service.ApiResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject


/**
 * Created by karn on 18/1/18.
 **/

/**
 * Extension function to convert a Publish subject into a LiveData by subscribing to it.
 **/
fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe({ t: T -> data.value = t }))
    return data
}

/**
 * Extension function to push a failed event with an exception to the observing outcome
 * */
fun <T> PublishSubject<ApiResult<T>>.failed(e: Throwable) {
    with(this){
        loading(false)
        onNext(ApiResult.failure(e))
    }
}

/**
 * Extension function to push  a success event with data to the observing outcome
 * */
fun <T> PublishSubject<ApiResult<T>>.success(t: T) {
    with(this){
        loading(false)
        onNext(ApiResult.success(t))
    }
}

/**
 * Extension function to push the loading status to the observing outcome
 * */
fun <T> PublishSubject<ApiResult<T>>.loading(isLoading: Boolean) {
    this.onNext(ApiResult.loading(isLoading))
}