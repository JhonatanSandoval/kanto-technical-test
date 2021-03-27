package pro.jsandoval.kantotest.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class ContextModule {

    @Binds
    internal abstract fun bindsContext(application: Application): Context

}