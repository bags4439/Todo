package com.appvengers.todo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Long?=null,
    @ColumnInfo(name = "task")
    var task: String?,
    @ColumnInfo(name = "description")
    var description: String?
    )