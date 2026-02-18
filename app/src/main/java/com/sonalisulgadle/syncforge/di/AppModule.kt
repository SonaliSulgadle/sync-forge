package com.sonalisulgadle.syncforge.di

import android.content.Context
import androidx.work.WorkManager
import com.sonalisulgadle.syncforge.data.repository.SyncRepositoryImpl
import com.sonalisulgadle.syncforge.domain.repository.SyncRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWorkManager(
        @ApplicationContext context: Context
    ): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideSyncRepository(
        workManager: WorkManager
    ): SyncRepository {
        return SyncRepositoryImpl(workManager)
    }
}
