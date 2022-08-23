package com.appvengers.todo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = true)
abstract class TodoDatabase : RoomDatabase(){
  abstract  val todoDao: TodoDao


  companion object{
      private var INSTANCE : TodoDatabase?=null

      fun getInstance(ctx: Context): TodoDatabase{
          synchronized(this){
              var instance: TodoDatabase? = INSTANCE
              if(instance==null){
                  instance = Room.databaseBuilder(
                     ctx.applicationContext,
                     TodoDatabase::class.java,
                     "todo_db"
                 ).build()
              }

              return instance
          }
      }
  }

}