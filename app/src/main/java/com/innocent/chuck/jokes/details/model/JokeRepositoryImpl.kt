package com.innocent.chuck.jokes.details.model

import com.innocent.chuck.core.extensions.*
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.core.service.IScheduler
import com.innocent.chuck.jokes.commons.JokeService
import com.innocent.chuck.jokes.commons.dto.Joke
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

class JokeRepositoryImpl(
    private val jokeService: JokeService,
    private val scheduler: IScheduler,
    private val compositeDisposable: CompositeDisposable
): JokeRepository{

    override val fetchJokeApiResult: PublishSubject<ApiResult<Joke>> = PublishSubject.create()

    override fun fetchJokeFor(category: String) {
       fetchJokeApiResult.loading(true)
        jokeService.getRandomJoke(category).performOnBackOutOnMain(scheduler)
            .subscribe(
                {joke -> handleApiSuccess(joke)},
                {error -> handleError(error)}
            ).addTo(compositeDisposable)
    }

    private fun handleApiSuccess(results: Joke){
        fetchJokeApiResult.success(results)
    }

    private fun handleError(error: Throwable){
        fetchJokeApiResult.failed(error)
    }
}