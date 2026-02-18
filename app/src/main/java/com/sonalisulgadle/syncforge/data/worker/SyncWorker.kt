package com.sonalisulgadle.syncforge.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        try {
            simulateWork()
            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }

    private suspend fun simulateWork() {
        delay(2000)
    }

}