package pro.jsandoval.kantotest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pro.jsandoval.kantotest.data.repository.RecordRepositoryImpl
import pro.jsandoval.kantotest.data.repository.UserRepositoryImpl
import pro.jsandoval.kantotest.domain.repository.RecordRepository
import pro.jsandoval.kantotest.domain.repository.UserRepository

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    internal abstract fun provideRecordRepository(recordRepositoryImpl: RecordRepositoryImpl): RecordRepository

    @Binds
    internal abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

}