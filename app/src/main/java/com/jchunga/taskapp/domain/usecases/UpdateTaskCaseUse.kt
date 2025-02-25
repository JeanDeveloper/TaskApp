package com.jchunga.taskapp.domain.usecases

import com.jchunga.taskapp.domain.entities.Task
import com.jchunga.taskapp.domain.repositories.TaskRepository
import javax.inject.Inject

class UpdateTaskCaseUse @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = repository.updateTask(task)
}