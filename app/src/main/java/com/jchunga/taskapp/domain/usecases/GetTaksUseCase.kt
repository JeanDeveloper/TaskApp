package com.jchunga.taskapp.domain.usecases

import com.jchunga.taskapp.domain.repositories.TaskRepository
import javax.inject.Inject

class GetTaksUseCase @Inject constructor( private val repository: TaskRepository)  {
    operator fun invoke() = repository.getTasks()
}