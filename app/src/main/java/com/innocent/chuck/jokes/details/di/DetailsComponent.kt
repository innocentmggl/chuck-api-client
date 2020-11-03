package com.innocent.chuck.jokes.details.di

import com.innocent.chuck.core.service.IScheduler
import com.innocent.chuck.jokes.details.DetailsActivity
import com.innocent.chuck.jokes.commons.JokeService
import com.innocent.chuck.jokes.details.model.JokeRepository
import com.innocent.chuck.jokes.details.model.JokeRepositoryImpl
import com.innocent.chuck.jokes.details.viewmodel.JokeViewModelFactory
import com.innocent.chuck.jokes.list.di.ListComponent
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@DetailsScope
@Component(dependencies = [ListComponent::class], modules = [DetailsModule::class])
interface DetailsComponent {
    fun inject(detailsActivity: DetailsActivity)
}

@Module
class DetailsModule {

    /*ViewModel*/
    @Provides
    @DetailsScope
    fun jokeViewModelFactory(repo: JokeRepository, picasso: Picasso, compositeDisposable: CompositeDisposable): JokeViewModelFactory {
        return JokeViewModelFactory(repo, picasso, compositeDisposable)
    }

    /*Repository*/
    @Provides
    @DetailsScope
    fun jokeRepo(service: JokeService, scheduler: IScheduler, compositeDisposable: CompositeDisposable)
            : JokeRepository = JokeRepositoryImpl(service, scheduler, compositeDisposable)

    @Provides
    @DetailsScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
}