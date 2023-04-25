package com.babysloth.memo.di

import android.content.Context
import com.babysloth.memo.data.database.MemoRoomDatabase
import com.babysloth.memo.data.repository.RoomMemoRepository
import com.babysloth.memo.data.room.dao.MemoDao
import com.babysloth.memo.domain.repository.MemoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMemoDatabase(
        @ApplicationContext context: Context
    ): MemoRoomDatabase {
        return MemoRoomDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideMemoDao(database: MemoRoomDatabase): MemoDao {
        return database.memoDao()
    }

    @Provides
    fun provideMemoRepository(dao: MemoDao) : MemoRepository {
        return RoomMemoRepository(dao)
    }

}
