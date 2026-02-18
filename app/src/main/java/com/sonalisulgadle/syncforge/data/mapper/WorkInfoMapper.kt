package com.sonalisulgadle.syncforge.data.mapper

import androidx.work.WorkInfo
import com.sonalisulgadle.syncforge.domain.model.SyncStatus

fun WorkInfo.toDomainSyncStatus(): SyncStatus {
    return when (state) {
        WorkInfo.State.ENQUEUED -> SyncStatus.Enqueued
        WorkInfo.State.RUNNING -> SyncStatus.Running
        WorkInfo.State.SUCCEEDED -> SyncStatus.Succeeded
        WorkInfo.State.FAILED -> SyncStatus.Failed()
        WorkInfo.State.CANCELLED -> SyncStatus.Canceled
        else -> SyncStatus.Idle
    }
}