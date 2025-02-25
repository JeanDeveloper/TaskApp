//package com.jchunga.taskapp.presentation.components
//
//import androidx.compose.animation.animateColorAsState
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material.DismissDirection
//import androidx.compose.material.DismissValue
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.FractionalThreshold
//import androidx.compose.material.SwipeToDismiss
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.rememberDismissState
//import androidx.compose.material3.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.jchunga.taskapp.domain.entities.Task
//
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun SwipeToDismissTaskCard(
//    modifier: Modifier = Modifier,
//    task: Task,
//    onDelete: () -> Unit = {},
//    onClickFavourite: () -> Unit = {},
//    onClick: () -> Unit = {}
//) {
//    val dismissState = rememberDismissState(
//        confirmStateChange = {
//            if ( it == DismissValue.DismissedToStart  ) {
//                onDelete() // Eliminar tarea al hacer swipe completo
//                true
//            } else {
//                false
//            }
//        }
//    )
//
//    SwipeToDismiss(
//        state = dismissState,
//        directions = setOf(DismissDirection.EndToStart),
//        dismissThresholds = { FractionalThreshold(0.5f) },
//        background = {
//
//            val color by animateColorAsState(
//                if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent
//            )
//
//            Box(
//                modifier = Modifier.fillMaxSize()
//                    .background(color)
//                    .padding(horizontal = 16.dp),
//                contentAlignment = Alignment.CenterEnd
//            ){
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Eliminar",
//                    tint = Color.White,
//                    modifier = Modifier.size(40.dp)
//
//                )
//            }
//        },
//        dismissContent = {
//            TaskCard(
//                modifier = modifier,
//                task = task,
//                onClick = onClick,
//                onClickFavourite = onClickFavourite
//            )
//        }
//    )
//
//
//}