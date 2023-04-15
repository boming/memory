package com.babysloth.memo.data.repository

import com.babysloth.memo.data.model.Memo
import kotlinx.coroutines.flow.Flow


interface MemoRepository {
    suspend fun create(memo: Memo)

    suspend fun update(memo: Memo)

    suspend fun delete(memo: Memo)

    fun getMemos(): Flow<List<Memo>>
    
    fun getMemo(id: Long): Flow<Memo>
}
