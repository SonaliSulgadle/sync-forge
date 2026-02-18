package com.sonalisulgadle.syncforge.data.repository

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.sonalisulgadle.syncforge.data.worker.SyncWorker
import com.sonalisulgadle.syncforge.domain.model.SyncStatus
import com.sonalisulgadle.syncforge.domain.repository.SyncRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class SyncRepositoryImpl(
    private val workManager: WorkManager
) : SyncRepository {
    override suspend fun enqueueSync() {
        val request = OneTimeWorkRequestBuilder<SyncWorker>()
            .setConstraints(buildConstraints())
            .setBackoffCriteria(
                BackoffPolicy.EXPONENTIAL,
                10,
                TimeUnit.SECONDS
            )
            .build()

        workManager.enqueueUniqueWork(
            UNIQUE_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }

    override fun observeSyncStatus(): Flow<SyncStatus> {
        return workManager.getWorkInfosForUniqueWorkFlow(UNIQUE_WORK_NAME)
            .map { workInfos ->
                workInfos?.firstOrNull()?.toDomainSyncStatus() ?: SyncStatus.Idle
            }
    }

    private fun buildConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
    }

    companion object {
        private const val UNIQUE_WORK_NAME = "SyncWorker"
    }

}