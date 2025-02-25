package com.jchunga.taskapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jchunga.taskapp.presentation.viewmodels.TaskViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jchunga.taskapp.presentation.components.CustomTextField
import com.jchunga.taskapp.presentation.components.LongTextField
import com.jchunga.taskapp.presentation.event.TasksEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUpTaskScreen(
    taskViewModel: TaskViewModel ,
    navController: NavHostController,
    taskId: Int? = null
){

    LaunchedEffect (taskId){
        taskViewModel.loadTask(taskId)
    }

    val task by taskViewModel.selectedTask.collectAsState()

    var title by taskViewModel.title
    var description by taskViewModel.description

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(243, 243, 254),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if(taskId != null) "Editar Tarea" else "Nueva Tarea",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                        )
                    )
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(it).padding(horizontal = 20.dp, vertical = 30.dp)
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { newText ->
                    title = newText
                 },
                label = "Titulo",
                leadingIcon = Icons.Default.Star
            )

            Spacer(modifier = Modifier.height(20.dp))

            LongTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onTextChange = { text ->
                    description = text
                },
                label = "Descripcion de la tarea"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    taskViewModel.onEvent(TasksEvent.AddUpdateTask(taskId))
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Guardar",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                    )
                )

            }

        }

    }
}

@Preview
@Composable
fun NewTaskScreenPreview(){
    val taskViewModel : TaskViewModel = hiltViewModel()
    val navController = NavHostController(LocalContext.current)
    NewUpTaskScreen(
        taskViewModel = taskViewModel,
        navController = navController
    )
}