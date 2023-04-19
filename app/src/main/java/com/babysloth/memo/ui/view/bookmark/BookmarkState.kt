package com.babysloth.memo.ui.view.bookmark

sealed class BookmarkState {
    object Uninitialized : BookmarkState()
    object Loading : BookmarkState()
    data class Fail(val message: String) : BookmarkState()
    object Success : BookmarkState()
}
