package com.inyomanw.nyomanavowstest.di

import android.content.Context
import androidx.room.Room
import com.inyomanw.nyomanavowstest.data.room.AppDatabase
import com.inyomanw.nyomanavowstest.data.room.dao.CartDao
import com.inyomanw.nyomanavowstest.data.room.dao.CategoryDao
import com.inyomanw.nyomanavowstest.data.room.dao.ProductDao
import com.inyomanw.nyomanavowstest.data.room.dao.UserDao
import com.inyomanw.nyomanavowstest.domain.repository.Repository
import com.inyomanw.nyomanavowstest.domain.repository.RepositoryImplementation
import com.inyomanw.nyomanavowstest.networking.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRepository(
        apiService: ApiService,
        userDao: UserDao,
        productDao: ProductDao,
//        cartDao: CartDao,
        categoryDao: CategoryDao
    ): Repository = RepositoryImplementation(apiService, userDao,productDao, categoryDao)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }
//
//    @Provides
//    fun providesCartDao(database: AppDatabase): CartDao {
//        return database.cartDao()
//    }

    @Provides
    fun providesCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }
}