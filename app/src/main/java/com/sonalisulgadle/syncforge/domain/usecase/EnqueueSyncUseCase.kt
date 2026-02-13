package com.sonalisulgadle.syncforge.domain.usecase

import com.sonalisulgadle.syncforge.domain.repository.SyncRepository

class EnqueueSyncUseCase(
    private val syncRepository: SyncRepository
) {
    suspend fun invoke() = syncRepository.enqueueSync()
}