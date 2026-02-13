package com.sonalisulgadle.syncforge.domain.model

sealed class SyncStatus {
    object Enqueued : SyncStatus()
    object Running : SyncStatus()
    object Succeeded : SyncStatus()
    object Canceled : SyncStatus()
    data class Failed(val error: String? = null) : SyncStatus()
}
