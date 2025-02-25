package com.jchunga.taskapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jchunga.taskapp.presentation.viewmodels.TaskViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskDetailScreen(
    taskViewModel: TaskViewModel,
    navController: NavHostController
){

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(243, 243, 254),

        ){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Task Detail Screen")
        }

    }

}