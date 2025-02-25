package com.jchunga.taskapp.presentation.nvgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jchunga.taskapp.presentation.viewmodels.TaskViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jchunga.taskapp.presentation.screens.HomeScreen
import com.jchunga.taskapp.presentation.screens.NewUpTaskScreen
import com.jchunga.taskapp.presentation.screens.TaskDetailScreen

@Composable
fun NavGraph(
    startDestination: String,
    taskViewModel: TaskViewModel = hiltViewModel(),
){
    val navController = rememberNavController()

    NavHost( navController = navController, startDestination = startDestination ){

        composable(
            route = Route.HomeScreen.route
        ){
            HomeScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

        composable(
            route = Route.NewUpTaskScreen.route + "?taskId={taskId}",
            arguments = listOf(
                navArgument("taskId"){
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ){ backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            NewUpTaskScreen(
                navController = navController,
                taskViewModel = taskViewModel,
                taskId = taskId
            )
        }

        composable(
            route = Route.TaskDetailScreen.route,
        ){
            TaskDetailScreen(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }

    }

}