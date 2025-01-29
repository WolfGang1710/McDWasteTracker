package fr.ensisa.cassier.mcdwastetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientSubCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.UnitType
import fr.ensisa.cassier.mcdwastetracker.ui.components.IngredientCard

@Composable
fun IngredientsScreen(
    ingredients: List<Ingredient>,
    onAddIngredient: (Ingredient) -> Unit,
    onEditIngredient: (Ingredient) -> Unit,
    onDeleteIngredient: (Ingredient) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var editingIngredient by remember { mutableStateOf<Ingredient?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ingrédients") },
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Ajouter un ingrédient")
                    }
                }
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
                        text = "Aucun ingrédient disponible",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn {
                        items(ingredients) { ingredient ->
                            IngredientCard(
                                ingredient = ingredient,
                                onEditClick = {
                                    editingIngredient = ingredient
                                    showDialog = true
                                },
                                onDeleteClick = onDeleteIngredient
                            )
                        }
                    }
                }
            }

            if (showDialog) {
                IngredientDialog(
                    ingredient = editingIngredient,
                    onDismiss = {
                        showDialog = false
                        editingIngredient = null
                    },
                    onSave = {
                        if (editingIngredient == null) {
                            onAddIngredient(it)
                        } else {
                            onEditIngredient(it)
                        }
                        showDialog = false
                        editingIngredient = null
                    }
                )
            }
        }
    )
}
