package com.example.marivramchoir.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marivramchoir.data.model.Hymn
import com.example.marivramchoir.data.model.Servant
import com.example.marivramchoir.data.model.ServantTranimRef
import com.example.marivramchoir.utlis.DATABASE_NAME


@Database(entities = [Hymn::class, Servant::class, ServantTranimRef::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao() : DatabaseDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context
                    //mDataBase
                ).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}