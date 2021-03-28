package pro.jsandoval.kantotest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pro.jsandoval.kantotest.data.local.room.KantoTestDatabase
import pro.jsandoval.kantotest.data.local.sp.PreferenceStorage
import pro.jsandoval.kantotest.data.local.sp.SharedPreferenceStorage
import pro.jsandoval.kantotest.work.WorkScheduler
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideWorkScheduler(context: Context) = WorkScheduler(context)

    @Provides
    @Singleton
    fun providePreferenceStorage(context: Context): PreferenceStorage = SharedPreferenceStorage(context)

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context) =
        Room.databaseBuilder(context, KantoTestDatabase::class.java, KantoTestDatabase.DB_NAME)
            .build()

}