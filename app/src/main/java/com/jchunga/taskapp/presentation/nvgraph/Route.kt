package com.jchunga.taskapp.presentation.nvgraph

sealed class Route(
    val route: String,
) {
    data object HomeScreen: Route("HomeScreen")
    data object NewUpTaskScreen: Route("NewUpTaskScreen")
    data object TaskDetailScreen: Route("TaskDetailScreen")
}