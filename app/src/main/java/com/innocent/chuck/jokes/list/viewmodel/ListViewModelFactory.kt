package com.innocent.chuck.jokes.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.innocent.chuck.jokes.list.model.ListRepository
import io.reactivex.disposables.CompositeDisposable

class ListViewModelFactory(private val repository: ListRepository, private val compositeDisposable: CompositeDisposable) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repository,compositeDisposable) as T
    }
}