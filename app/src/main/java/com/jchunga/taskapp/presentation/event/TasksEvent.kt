package com.jchunga.taskapp.presentation.event

import com.jchunga.taskapp.domain.entities.Task

sealed class TasksEvent {

    data class AddUpdateTask(val taskId: Int?): TasksEvent()

//    data class AddTask() :  TasksEvent()
//    data class UpdateTask(val task: Task) : TasksEvent()
}