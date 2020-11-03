package com.innocent.chuck.jokes.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.innocent.chuck.core.extensions.toLiveData
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.jokes.commons.JokesDH
import com.innocent.chuck.jokes.commons.dto.Joke
import com.innocent.chuck.jokes.details.model.JokeRepository
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable

class JokeViewModel(
    private val repository: JokeRepository,
    private val picasso: Picasso,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val fetchJokeOutcome: LiveData<ApiResult<Joke>> by lazy {
        repository.fetchJokeApiResult.toLiveData(compositeDisposable)
    }

    fun fetchJokeFor(category: String){
        repository.fetchJokeFor(category)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        JokesDH.destroyDetailsComponent()
    }
}
