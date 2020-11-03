package com.innocent.chuck.jokes.details.model

import com.innocent.chuck.core.service.ApiResult
import com.innocent.chuck.jokes.commons.dto.Joke
import io.reactivex.subjects.PublishSubject

interface JokeRepository {
    val fetchJokeApiResult: PublishSubject<ApiResult<Joke>>
    fun fetchJokeFor(category: String)
}