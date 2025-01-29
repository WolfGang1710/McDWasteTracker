package fr.ensisa.cassier.mcdwastetracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import fr.ensisa.cassier.mcdwastetracker.ui.screens.IngredientsScreen
import fr.ensisa.cassier.mcdwastetracker.ui.screens.LossesScreen
import fr.ensisa.cassier.mcdwastetracker.ui.screens.SandwichesScreen
import fr.ensisa.cassier.mcdwastetracker.ui.screens.TrashScreen
import fr.ensisa.cassier.mcdwastetracker.ui.viewmodel.IngredientsViewModel
import fr.ensisa.cassier.mcdwastetracker.ui.viewmodel.LossesViewModel
import fr.ensisa.cassier.mcdwastetracker.ui.viewmodel.SandwichesViewModel
import fr.ensisa.cassier.mcdwastetracker.ui.viewmodel.TrashViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = "losses"
) {
    val lossesViewModel: LossesViewModel = hiltViewModel()
    val sandwichesViewModel: SandwichesViewModel = hiltViewModel()
    val ingredientsViewModel: IngredientsViewModel = hiltViewModel()
    val trashViewModel: TrashViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("losses") {
            LossesScreen(
                ingredients = lossesViewModel.ingredients.collectAsState().value,
                quantities = lossesViewModel.quantities.collectAsState().value,
                onIncrement = { lossesViewModel.incrementQuantity(it) },
                onDecrement = { lossesViewModel.decrementQuantity(it) }
            )
        }
        composable("sandwiches") {
            SandwichesScreen(
                sandwiches = sandwichesViewModel.sandwiches.collectAsState().value,
                onAddSandwich = { sandwichesViewModel.addSandwich(it) },
                onEditSandwich = { sandwichesViewModel.editSandwich(it) },
                onDeleteSandwich = { sandwichesViewModel.deleteSandwich(it) }
            )
        }
        composable("ingredients") {
            IngredientsScreen(
                ingredients = ingredientsViewModel.ingredients.collectAsState().value,
                onAddIngredient = { ingredientsViewModel.addIngredient(it) },
                onEditIngredient = { ingredientsViewModel.editIngredient(it) },
                onDeleteIngredient = { ingredientsViewModel.deleteIngredient(it) }
            )
        }
        composable("trash") {
            TrashScreen(
                currentQuart = trashViewModel.currentQuart.collectAsState().value,
                onOpenTrash = { quart, time -> trashViewModel.openTrash(quart, time) },
                onCloseTrash = { time -> trashViewModel.closeTrash(time) },
                onResetTrash = { trashViewModel.resetTrash() }
            )
        }
    }
}
