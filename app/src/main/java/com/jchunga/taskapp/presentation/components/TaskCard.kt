package com.jchunga.taskapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jchunga.taskapp.domain.entities.Task
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit = {},
    onClickFavourite: () -> Unit = {}
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        onClick = onClick
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Icono Circular
            CircleAvatar(
                modifier = modifier,
                size = 45.dp,
                backColor = MaterialTheme.colorScheme.primary,
                icon = {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "User",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            )

            Spacer(modifier = modifier.width(12.dp))

            // Texto de la tarea
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = formatRelativeDateTime(task.date),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            IconButton(
                onClick = onClickFavourite
            ) {
                Icon(
                    imageVector = if(task.favourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "More Options",
                    tint = Color.Gray
                )
            }

        }
    }

}

@SuppressLint("NewApi")
fun formatRelativeDateTime(dateTime: LocalDateTime):String{
    val today = LocalDate.now()
    val datePart = dateTime.toLocalDate()
    val timePart = dateTime.toLocalTime()

    val daysBetween = ChronoUnit.DAYS.between(datePart, today)

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm a")
    val formattedTime = timePart.format(timeFormatter)

    val relativeDate = when (daysBetween) {
        0L -> "Hoy"
        1L -> "Hace un dia"
        else -> "Hace $daysBetween dias"
    }

    return "$relativeDate.   $formattedTime"
}


@Preview
@Composable
fun TaskCardPreview(){
    val task = Task(
        title ="Proyecto de la Tarea 1",
        description = "Esta es la descripcion de la tarea",
        favourite = false,
        date = LocalDateTime.now()
    )

    TaskCard(
        modifier = Modifier,
        task = task
    )

}