package com.babysloth.memo.di

import android.content.Context
import com.babysloth.memo.data.database.MemoRoomDatabase
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
}
