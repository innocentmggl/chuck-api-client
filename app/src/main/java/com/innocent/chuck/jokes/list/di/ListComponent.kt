package com.innocent.chuck.jokes.list.di

import com.innocent.chuck.core.di.CoreComponent
import com.innocent.chuck.core.service.IScheduler
import com.innocent.chuck.jokes.ListActivity
import com.innocent.chuck.jokes.commons.JokeService
import com.innocent.chuck.jokes.list.model.ListRepository
import com.innocent.chuck.jokes.list.model.ListRepositoryImpl
import com.innocent.chuck.jokes.list.viewmodel.ListViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@ListScope
@Component(dependencies = [CoreComponent::class], modules = [ListModule::class])
interface ListComponent {

    fun jokeService(): JokeService
    fun picasso(): Picasso
    fun scheduler(): IScheduler

    fun inject(listActivity: ListActivity)
}

@Module
@ListScope
class ListModule {

    /*ViewModel*/
    @Provides
    @ListScope
    fun listViewModelFactory(repository: ListRepository,compositeDisposable: CompositeDisposable): ListViewModelFactory = ListViewModelFactory(repository,compositeDisposable)

    /*Repository*/
    @Provides
    @ListScope
    fun listRepo(service: JokeService, scheduler: IScheduler, compositeDisposable: CompositeDisposable): ListRepository = ListRepositoryImpl(service, scheduler, compositeDisposable)

    @Provides
    @ListScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()


    @Provides
    @ListScope
    fun jokeService(retrofit: Retrofit): JokeService = retrofit.create(JokeService::class.java)
}