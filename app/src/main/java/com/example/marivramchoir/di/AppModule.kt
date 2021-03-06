package com.example.marivramchoir.di

import android.content.Context
import com.example.marivramchoir.data.api.HymnApiImpl
import com.example.marivramchoir.data.api.MarIvramApi
import com.example.marivramchoir.data.database.AppDatabase
import com.example.marivramchoir.data.database.DatabaseDao
import com.example.marivramchoir.data.database.DatabaseHelper
import com.example.marivramchoir.data.repository.AddServantRepository
import com.example.marivramchoir.data.repository.AddTarnimaRepository
import com.example.marivramchoir.data.repository.AllHymensByAlbumRepository
import com.example.marivramchoir.data.repository.AllHymensRepository
import com.example.marivramchoir.utlis.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesUrl() = BASE_URL

    @Provides
    @Singleton
    fun providesApiService(url:String) : MarIvramApi =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarIvramApi::class.java)


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)


    @Singleton
    @Provides
    fun provideDataDao(db: AppDatabase) = db.getDao()

    @Singleton
    @Provides
    fun provideDatabaseHelper(databaseDao: DatabaseDao) = DatabaseHelper(databaseDao)


    @Singleton
    @Provides
    fun provideApiRemoteDataSource(apiService: MarIvramApi) = HymnApiImpl(apiService)


    @Singleton
    @Provides
    fun provideGetAllHymensRepository(
        apiServiceImpl: HymnApiImpl, databaseHelper: DatabaseHelper
    ) = AllHymensRepository(apiServiceImpl, databaseHelper)


    @Singleton
    @Provides
    fun provideAddTarnimaRepository(
        apiServiceImpl: HymnApiImpl
    ) = AddTarnimaRepository(apiServiceImpl)

    @Singleton
    @Provides
    fun provideAddServantRepository(
        apiServiceImpl: HymnApiImpl
    ) = AddServantRepository(apiServiceImpl)

    @Singleton
    @Provides
    fun provideGetAllHymensByAlbumRepository(
        databaseHelper: DatabaseHelper
    ) = AllHymensByAlbumRepository(databaseHelper)
}