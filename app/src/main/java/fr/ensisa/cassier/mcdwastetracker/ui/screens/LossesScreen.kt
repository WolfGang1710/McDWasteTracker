package fr.ensisa.cassier.mcdwastetracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import fr.ensisa.cassier.mcdwastetracker.ui.components.LossItemCard

@Composable
fun LossesScreen(
    ingredients: List<Ingredient>,
    quantities: Map<Int, Int>,
    onIncrement: (Ingredient) -> Unit,
    onDecrement: (Ingredient) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pertes") }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                if (ingredients.isEmpty()) {
                    Text(
                        text = "Aucune perte enregistrée.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Affiche les ingrédients regroupés par catégorie
                        IngredientCategory.values().forEach { category ->
                            val filteredIngredients = ingredients.filter { it.category == category }
                            if (filteredIngredients.isNotEmpty()) {
                                item {
                                    Text(
                                        text = category.name,
                                        style = MaterialTheme.typography.h3,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                                items(filteredIngredients) { ingredient ->
                                    LossItemCard(
                                        ingredient = ingredient,
                                        quantity = quantities[ingredient.id] ?: 0,
                                        onIncrement = onIncrement,
                                        onDecrement = onDecrement
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
