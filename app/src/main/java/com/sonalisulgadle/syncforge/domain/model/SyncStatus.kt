package com.sonalisulgadle.syncforge.domain.model

enum class SyncStatus {
    ENQUEUED,
    RUNNING,
    SUCCEEDED,
    FAILED,
    CANCELED
}