package com.sonalisulgadle.syncforge.domain.usecase

import com.sonalisulgadle.syncforge.domain.repository.SyncRepository

class ObserveSyncStatusUseCase(
    private val syncRepository: SyncRepository
) {
    fun invoke() = syncRepository.observeSyncStatus()
}