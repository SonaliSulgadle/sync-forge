package com.sonalisulgadle.syncforge.domain.repository

import com.sonalisulgadle.syncforge.domain.model.SyncStatus
import kotlinx.coroutines.flow.Flow

interface SyncRepository {
    fun enqueueSync()
    fun observeSyncStatus(): Flow<SyncStatus>
}