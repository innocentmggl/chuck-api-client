package com.innocent.chuck.jokes.list.model

import com.innocent.chuck.core.service.ApiResult
import io.reactivex.subjects.PublishSubject

interface ListRepository {
    val fetchCategoriesApiResult: PublishSubject<ApiResult<List<String>>>
    fun fetchCategories()
}