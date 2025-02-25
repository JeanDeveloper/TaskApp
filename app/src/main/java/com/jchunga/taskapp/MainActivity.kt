package com.jchunga.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.jchunga.taskapp.ui.theme.TaskAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jchunga.taskapp.presentation.nvgraph.NavGraph
import com.jchunga.taskapp.presentation.nvgraph.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskAppTheme {
                val isSystemInDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemInDarkMode
                    )
                }

                Box(
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.background
                    )
                ){
                    val startDestination = Route.HomeScreen.route
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
