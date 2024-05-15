package com.bk.indiatimes.presentation.screen

sealed class NewsDetailsScreenEvent {
    data class UpdateScrollPosition(val position: Int) : NewsDetailsScreenEvent()
}
