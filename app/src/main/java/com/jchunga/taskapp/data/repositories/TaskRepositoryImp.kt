package com.jchunga.taskapp.data.repositories

import com.jchunga.taskapp.data.datasource.local.TaskDao
import com.jchunga.taskapp.domain.entities.Task
import com.jchunga.taskapp.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImp(
    private val dao: TaskDao
): TaskRepository {
    override fun getTasks(): Flow<List<Task>> {
        return dao.getAllTasks()
    }

    override fun getTaskById(id: Int): Flow<Task?> {
        return dao.getTaskById(id)
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(task)
    }

    override suspend fun deleteTask(taskId: Int) {
        dao.deleteTask(taskId)
    }

    override suspend fun updateTask(task: Task) {
        dao.updateTask(task)
    }
}