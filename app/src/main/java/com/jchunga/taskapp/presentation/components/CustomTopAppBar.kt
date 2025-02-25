package com.jchunga.taskapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jchunga.taskapp.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    modifier: Modifier = Modifier
){
    TopAppBar(
        modifier = modifier.padding(horizontal = 10.dp),
        title = {},
        navigationIcon = {
            Image(
                modifier = modifier.size(40.dp),
                painter = painterResource(id = R.drawable.ic_menu_hamburger),
                contentDescription = "Logo",

            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(243, 243, 254),
        ),
        actions = {
            CircleAvatar(
                modifier = modifier,
                size = 35.dp,
                backColor = MaterialTheme.colorScheme.primary,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )

        }

    )

}