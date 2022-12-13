package com.sem08.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sem08.model.Lugar


@Database(entities = [Lugar::class], version = 1, exportSchema = false)

abstract class lugarDatabase () : RoomDatabase(){
    abstract fun lugarDao() : LugarDao

    companion object{
        @Volatile
        private var INSTANCE: lugarDatabase? = null

        fun getDatabase(context: android.content.Context) : lugarDatabase{
            val temp = INSTANCE
            if (temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    lugarDatabase::class.java,
                    "lugar_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}