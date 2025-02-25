package com.jchunga.taskapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jchunga.taskapp.domain.entities.Task


@Database(entities= [Task::class], version = 2)
@TypeConverters(TasksTypeConverter::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
}