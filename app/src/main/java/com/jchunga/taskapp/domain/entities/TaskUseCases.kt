package com.jchunga.taskapp.domain.entities

import com.jchunga.taskapp.domain.usecases.AddTaskUseCase
import com.jchunga.taskapp.domain.usecases.DeleteTaskCaseUse
import com.jchunga.taskapp.domain.usecases.GetTaksUseCase
import com.jchunga.taskapp.domain.usecases.GetTaskByIdUseCase
import com.jchunga.taskapp.domain.usecases.UpdateTaskCaseUse

data class TaskUseCases(
    val getTasksUseCase: GetTaksUseCase,
    val getTaskByIdUseCase: GetTaskByIdUseCase,
    val addTaskUseCase: AddTaskUseCase,
    val deleteTaskCaseUse: DeleteTaskCaseUse,
    val updateTaskUseCase: UpdateTaskCaseUse
)
