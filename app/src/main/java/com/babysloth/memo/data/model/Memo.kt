package com.babysloth.memo.data.model

import com.babysloth.memo.data.room.entity.MemoEntity


data class Memo(
    val id: Long = 0,
    val createdAt: Long,
    val updatedAt: Long,
    val title: String,
    val description: String,
    val isBookmark: Boolean = false
) {
    companion object {
        fun from(memoEntity: MemoEntity) = Memo(
            id = memoEntity.id,
            createdAt = memoEntity.createdAt,
            updatedAt = memoEntity.updatedAt,
            title = memoEntity.title,
            description = memoEntity.description,
            isBookmark = memoEntity.isBookmark
        )
    }
}

fun Memo.toMemo(): com.babysloth.memo.domain.model.Memo {
    return com.babysloth.memo.domain.model.Memo(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        title = title,
        description = description,
        isBookmark = isBookmark
    )
}
