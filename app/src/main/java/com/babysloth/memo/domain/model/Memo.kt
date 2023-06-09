package com.babysloth.memo.domain.model

data class Memo(
    val id: Long = 0,
    val createdAt: Long = 0,
    val updatedAt: Long = 0,
    val title: String = "",
    val description: String = "",
    val isBookmark: Boolean = false
)
