package com.jchunga.taskapp.core

import android.app.Application
import androidx.room.Room
import com.jchunga.taskapp.data.datasource.local.TaskDao
import com.jchunga.taskapp.data.datasource.local.TaskDatabase
import com.jchunga.taskapp.data.datasource.local.TasksTypeConverter
import com.jchunga.taskapp.data.repositories.TaskRepositoryImp
import com.jchunga.taskapp.domain.entities.TaskUseCases
import com.jchunga.taskapp.domain.repositories.TaskRepository
import com.jchunga.taskapp.domain.usecases.AddTaskUseCase
import com.jchunga.taskapp.domain.usecases.DeleteTaskCaseUse
import com.jchunga.taskapp.domain.usecases.GetTaksUseCase
import com.jchunga.taskapp.domain.usecases.GetTaskByIdUseCase
import com.jchunga.taskapp.domain.usecases.UpdateTaskCaseUse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDao: TaskDao
    ): TaskRepository = TaskRepositoryImp(dao = taskDao)

    @Provides
    @Singleton
    fun provideTasksUseCases(
        taskRepository: TaskRepository
    ) = TaskUseCases(
        getTasksUseCase = GetTaksUseCase(taskRepository),
        getTaskByIdUseCase = GetTaskByIdUseCase(taskRepository),
        addTaskUseCase = AddTaskUseCase(taskRepository),
        deleteTaskCaseUse = DeleteTaskCaseUse(taskRepository),
        updateTaskUseCase = UpdateTaskCaseUse(taskRepository)
    )

    @Provides
    @Singleton
    fun provideTasksDatabase(
        application: Application
    ): TaskDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = TaskDatabase::class.java,
            name = "tasks_db"
        )
            .addTypeConverter(TasksTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTasksDao(
        tasksDatabase: TaskDatabase
    ): TaskDao = tasksDatabase.taskDao


}