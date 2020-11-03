package com.innocent.chuck.jokes.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.innocent.chuck.jokes.details.model.JokeRepository
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable

class JokeViewModelFactory(
    private val repository: JokeRepository,
    private val picasso: Picasso,
    private val compositeDisposable: CompositeDisposable
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JokeViewModel(repository, picasso, compositeDisposable) as T
    }
}