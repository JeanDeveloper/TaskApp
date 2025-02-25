package com.jchunga.taskapp.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jchunga.taskapp.domain.entities.Task
import com.jchunga.taskapp.domain.entities.TaskUseCases
import com.jchunga.taskapp.presentation.event.TasksEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

    var title = mutableStateOf("")
    var description = mutableStateOf("")

    private val _taskEvent = MutableSharedFlow<TasksEvent>()
    val taskEvent = _taskEvent.asSharedFlow()

    fun loadTask(taskId: Int?){
        if(taskId == null) {
            title.value = ""
            description.value = ""
            _selectedTask.value = null
            return
        }
        viewModelScope.launch {
            taskUseCases.getTaskByIdUseCase(taskId)
                .collectLatest { task ->
                    _selectedTask.value = task
                    task?.let {
                        title.value = it.title
                        description.value = it.description
                    }

                }
        }
    }

    val tasks = taskUseCases.getTasksUseCase().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )

    fun addTask(task: Task) = viewModelScope.launch {
        taskUseCases.addTaskUseCase(task)
    }

    fun toggleTaskFavourite(task: Task) = viewModelScope.launch {
        taskUseCases.updateTaskUseCase(task.copy(favourite = !task.favourite))
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        taskUseCases.deleteTaskCaseUse(task.id)
    }

    fun getTaskById(id: Int) = taskUseCases.getTaskByIdUseCase(id)

    fun onEvent(event: TasksEvent){
        when(event){
            is TasksEvent.AddUpdateTask -> {
                viewModelScope.launch {
                    if(event.taskId == null) {
                        val newTask = Task(
                            title = title.value,
                            description = description.value,
                            date = LocalDateTime.now()
                        )
                        taskUseCases.addTaskUseCase(newTask)
                    } else {
                        _selectedTask.value?.let {
                            it.title = title.value
                            it.description = description.value
                        }
                        _selectedTask.value?.let { taskUseCases.updateTaskUseCase(it) }
                    }
                }
            }
        }

    }

}