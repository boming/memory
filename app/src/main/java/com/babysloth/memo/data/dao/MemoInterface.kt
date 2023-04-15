package com.babysloth.memo.data.dao

import androidx.room.*
import com.babysloth.memo.data.model.MemoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MemoInterface {
    @Insert
    suspend fun insertMemo(memo: MemoEntity)

    @Update
    suspend fun updateMemo(memo: MemoEntity)

    @Delete
    suspend fun deleteMemo(memo: MemoEntity)

    @Query("SELECT * from memo WHERE id = :id")
    fun getMemoById(id: Long): Flow<MemoEntity>

    @Query("SELECT * from memo ORDER BY updated_at LIMIT :limit OFFSET :offset")
    fun getMemos(limit: Int, offset: Int = 0): Flow<List<MemoEntity>>
}
