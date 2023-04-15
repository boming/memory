package com.babysloth.memo.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.babysloth.memo.data.model.Memo

@Entity(tableName = "memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "create_at")
    val createdAt: Long,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long,
    val title: String,
    val description: String,
    @ColumnInfo(name = "is_bookmark")
    val isBookmark: Boolean = false
) {
    companion object {
        fun from(memo: Memo) = MemoEntity(
            id = memo.id,
            createdAt = memo.createdAt,
            updatedAt = memo.updatedAt,
            title = memo.title,
            description = memo.description,
            isBookmark = memo.isBookmark
        )
    }
}

fun MemoEntity.toMemo() = Memo(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    title = title,
    description = description,
    isBookmark = isBookmark
)
