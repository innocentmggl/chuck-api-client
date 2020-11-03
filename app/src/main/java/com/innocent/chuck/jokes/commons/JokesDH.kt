package com.innocent.chuck.jokes.commons

import com.innocent.chuck.core.application.CoreApp
import com.innocent.chuck.jokes.details.di.DaggerDetailsComponent
import com.innocent.chuck.jokes.details.di.DetailsComponent
import com.innocent.chuck.jokes.list.di.DaggerListComponent
import com.innocent.chuck.jokes.list.di.ListComponent
import javax.inject.Singleton

@Singleton
object JokesDH {
    private var listComponent: ListComponent? = null
    private var detailsComponent: DetailsComponent? = null

    fun listComponent(): ListComponent {
        if (listComponent == null)
            listComponent = DaggerListComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return listComponent as ListComponent
    }

    fun destroyListComponent() {
        listComponent = null
    }

    fun detailsComponent(): DetailsComponent {
        if (detailsComponent == null)
            detailsComponent = DaggerDetailsComponent.builder().listComponent(listComponent()).build()
        return detailsComponent as DetailsComponent
    }

    fun destroyDetailsComponent() {
       detailsComponent = null
    }
}