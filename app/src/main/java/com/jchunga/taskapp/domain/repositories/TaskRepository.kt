package com.jchunga.taskapp.domain.repositories

import com.jchunga.taskapp.domain.entities.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>

    fun getTaskById(id: Int): Flow<Task?>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(taskId: Int)

    suspend fun updateTask(task: Task)

}