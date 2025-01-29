package fr.ensisa.cassier.mcdwastetracker.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientSubCategory

@Composable
fun DropdownMenuSubCategory(
    selectedSubCategory: IngredientSubCategory?,
    onSubCategoryChange: (IngredientSubCategory?) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = selectedSubCategory?.name ?: "Aucune",
            onValueChange = {},
            label = { Text("Sous-catégorie") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Afficher les sous-catégories"
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Aucune") },
                onClick = {
                    onSubCategoryChange(null)
                    expanded = false
                }
            )

            IngredientSubCategory.values().forEach { subCategory ->
                DropdownMenuItem(
                    text = { Text(subCategory.name) },
                    onClick = {
                        onSubCategoryChange(subCategory)
                        expanded = false
                    }
                )
            }
        }
    }
}
