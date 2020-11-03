package com.innocent.chuck.jokes.commons

import com.innocent.chuck.jokes.commons.dto.Joke
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeService {

    @GET("v1/jokes/categories")
    fun getCategories(): Single<List<String>>

    @GET("v1/jokes/random/{category}")
    fun getRandomJoke(@Path("category")  category: String): Single<Joke>
}