package fr.ensisa.cassier.mcdwastetracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import fr.ensisa.cassier.mcdwastetracker.ui.components.DropdownMenuCategory
import fr.ensisa.cassier.mcdwastetracker.ui.components.DropdownMenuSubCategory
import fr.ensisa.cassier.mcdwastetracker.ui.components.DropdownMenuUnit

@Composable
fun IngredientDialog(
    ingredient: Ingredient?,
    onDismiss: () -> Unit,
    onSave: (Ingredient) -> Unit
) {
    var name by remember { mutableStateOf(ingredient?.name ?: "") }
    var category by remember { mutableStateOf(ingredient?.category ?: IngredientCategory.PROTEINS) }
    var subCategory by remember { mutableStateOf(ingredient?.subCategory) }
    var unit by remember { mutableStateOf(ingredient?.unit) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(
                            Ingredient(
                                id = ingredient?.id ?: 0,
                                name = name,
                                category = category,
                                subCategory = subCategory,
                                unit = unit
                            )
                        )
                    }
                }
            ) {
                Text("Enregistrer")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Annuler") }
        },
        title = { Text(text = if (ingredient == null) "Ajouter un ingrédient" else "Modifier l'ingrédient") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nom") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                DropdownMenuCategory(
                    selectedCategory = category,
                    onCategoryChange = { category = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                DropdownMenuSubCategory(
                    selectedSubCategory = subCategory,
                    onSubCategoryChange = { subCategory = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                DropdownMenuUnit(
                    selectedUnit = unit,
                    onUnitChange = { unit = it }
                )
            }
        }
    )
}
