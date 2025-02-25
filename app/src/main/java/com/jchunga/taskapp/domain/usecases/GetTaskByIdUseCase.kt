package com.jchunga.taskapp.domain.usecases

import com.jchunga.taskapp.domain.entities.Task
import com.jchunga.taskapp.domain.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(id: Int): Flow<Task?> = repository.getTaskById(id)
}