package com.jchunga.taskapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jchunga.taskapp.presentation.components.CustomBottomBar
import com.jchunga.taskapp.presentation.components.CustomTopAppBar
import com.jchunga.taskapp.presentation.components.TaskCard
import com.jchunga.taskapp.presentation.nvgraph.Route
import com.jchunga.taskapp.presentation.viewmodels.TaskViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel,
    navController: NavController
){
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(243, 243, 254),
        topBar = {
            CustomTopAppBar(modifier = modifier)
        },
        bottomBar = { CustomBottomBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate( route = Route.NewUpTaskScreen.route )
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 30.sp
                )
            }
        }
    ){
        HomeScreenContent(
            padding= it,
            taskViewModel = taskViewModel,
            navController = navController
        )
    }

}


@SuppressLint("NewApi")
@Composable
fun HomeScreenContent(
    padding: PaddingValues,
    taskViewModel: TaskViewModel,
    navController: NavController
){
    val taskList by taskViewModel.tasks.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(
                horizontal = 20.dp,
                vertical = 30.dp
            )
            .padding(padding)
    ) {
        Text(
            text = "Tasks",
            style = TextStyle(
                fontSize = 35.sp,
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold
            )
        )

        if(taskList.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No tasks found",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
        } else {
            LazyColumn {
                items(taskList){ task ->
                    TaskCard(
                        modifier = Modifier,
                        task = task,
                        onClick = {
                            navController.navigate(route = Route.NewUpTaskScreen.route + "?taskId=${task.id}")
                        },
                        onClickFavourite = {
                            taskViewModel.toggleTaskFavourite(task)
                        }

                    )



//                    SwipeToDismissTaskCard(
//                        task = task,
//                        onDelete = {
//                            taskViewModel.deleteTask(task)
//                        },
//                        onClickFavourite = {
//                            taskViewModel.toggleTaskFavourite(task)
//                        },
//                        onClick = {
//                            navController.navigate(route = Route.NewUpTaskScreen.route + "?taskId=${task.id}")
//                        }
//
//                    )


                }
            }
        }
    }
}
