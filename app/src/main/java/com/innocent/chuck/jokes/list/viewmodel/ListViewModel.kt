package com.innocent.chuck.jokes.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.innocent.chuck.core.extensions.toLiveData
import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.jokes.commons.JokesDH
import com.innocent.chuck.jokes.dummy.DummyContent
import com.innocent.chuck.jokes.list.model.ListRepository
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList
import java.util.HashMap

class ListViewModel(
    private val repo: ListRepository,
    private val compositeDisposable: CompositeDisposable
): ViewModel() {

    val fetchCategoryOutcome: LiveData<ApiResult<List<String>>> by lazy {
        repo.fetchCategoriesApiResult.toLiveData(compositeDisposable)
    }

    fun fetchCategories(){
        repo.fetchCategories()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        JokesDH.destroyListComponent()
    }
}