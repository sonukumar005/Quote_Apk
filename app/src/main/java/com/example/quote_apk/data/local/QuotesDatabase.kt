package com.example.quote_apk.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quote_apk.data.local.dao.Dao
import com.example.quote_apk.data.local.entity.QuotesEntity


@Database(entities = [QuotesEntity::class], version = 2, exportSchema = false)
 abstract class QuotesDatabase :RoomDatabase(){
     abstract val dao: Dao

     companion object {
         const val DATABASE_NAME = "quotes_database"
     }
}