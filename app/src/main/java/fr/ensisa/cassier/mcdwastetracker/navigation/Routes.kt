package fr.ensisa.cassier.mcdwastetracker.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavRoute(val route: String, val icon: ImageVector, val title: String) {
    object Losses : NavRoute("losses", Icons.Default.Home, "Pertes")
    object Sandwiches : NavRoute("sandwiches", Icons.Default.Check, "Sandwichs")
    object Ingredients : NavRoute("ingredients", Icons.Default.CheckCircle, "Ingrédients")
    object Trash : NavRoute("trash", Icons.Default.Delete, "Poubelle")
}

val navRoutes = listOf(
    NavRoute.Losses,
    NavRoute.Sandwiches,
    NavRoute.Ingredients,
    NavRoute.Trash
)
