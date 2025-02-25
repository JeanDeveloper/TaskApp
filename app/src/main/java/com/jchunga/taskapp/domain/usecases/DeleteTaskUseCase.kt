package com.jchunga.taskapp.domain.usecases

import com.jchunga.taskapp.domain.repositories.TaskRepository
import javax.inject.Inject

class DeleteTaskCaseUse @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteTask(id)
}

