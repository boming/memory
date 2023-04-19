package com.babysloth.memo.data.room.dao

import androidx.room.*
import com.babysloth.memo.data.room.entity.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoDao {
    @Insert
    suspend fun insertMemo(memo: MemoEntity)

    @Update
    suspend fun updateMemo(memo: MemoEntity)

    @Delete
    suspend fun deleteMemo(memo: MemoEntity)

    @Query("SELECT * from memo WHERE id = :id")
    fun getMemoById(id: Long): Flow<MemoEntity>

    @Query("SELECT * from memo ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    fun getMemos(limit: Int = 10, offset: Int = 0): Flow<List<MemoEntity>>

    @Query("SELECT * from memo WHERE is_bookmark = :isBookmark ORDER BY updated_at DESC LIMIT :limit OFFSET :offset")
    fun getBookmarkMemos(
        isBookmark: Boolean = true,
        limit: Int = 10,
        offset: Int = 0
    ): Flow<List<MemoEntity>>

}
