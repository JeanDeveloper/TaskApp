package com.jchunga.taskapp.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    modifier: Modifier = Modifier
){
    var selectedIndex by remember { mutableIntStateOf(0) }

    val items = listOf(
        BottomNavItem("Home", Icons.Filled.Home),
        BottomNavItem("Calendar", Icons.Rounded.DateRange),
        BottomNavItem("Notifications", Icons.Outlined.Notifications),
        BottomNavItem("Search", Icons.Outlined.Search)
    )

    BottomNavigation(
        modifier = modifier
            .padding(bottom =  WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() ),
        backgroundColor = Color.White,
        elevation = 20.dp
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label, modifier = Modifier.size(27.dp)) },
                selected = selectedIndex == index,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Gray,
                onClick = { selectedIndex = index }
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    )