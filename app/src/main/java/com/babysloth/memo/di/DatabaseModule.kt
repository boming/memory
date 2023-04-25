package com.babysloth.memo.di

import android.content.Context
import com.babysloth.memo.data.database.MemoRoomDatabase
import com.babysloth.memo.data.room.dao.MemoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideMemoDatabase(
        @ApplicationContext context: Context
    ): MemoRoomDatabase {
        return MemoRoomDatabase.getDatabase(context)
    }

    @Provides
    fun provideMemoDao(database: MemoRoomDatabase): MemoDao {
        return database.memoDao()
    }

}
