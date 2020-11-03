package com.innocent.chuck.jokes.list.model

import com.innocent.chuck.core.extensions.*
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.core.service.IScheduler
import com.innocent.chuck.jokes.commons.JokeService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class ListRepositoryImpl(
        private val jokeService: JokeService,
        private val scheduler: IScheduler,
        private val compositeDisposable: CompositeDisposable
    ) : ListRepository {

    override val fetchCategoriesApiResult: PublishSubject<ApiResult<List<String>>> = PublishSubject.create()

    override fun fetchCategories() {
        fetchCategoriesApiResult.loading(true)
        jokeService.getCategories().performOnBackOutOnMain(scheduler)
            .subscribe(
                { results -> handleApiSuccess(results) },
                {error -> handleError(error)}
            ).addTo(compositeDisposable)
    }

    private fun handleApiSuccess(results: List<String>){
        fetchCategoriesApiResult.success(results)
    }

    private fun handleError(error: Throwable){
        fetchCategoriesApiResult.failed(error)
    }
}

