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
import fr.ensisa.cassier.mcdwastetracker.models.Product
import fr.ensisa.cassier.mcdwastetracker.models.enums.ProductCategory
import fr.ensisa.cassier.mcdwastetracker.ui.components.DropdownMenuProductCategory

@Composable
fun SandwichDialog(
    sandwich: Product?,
    onDismiss: () -> Unit,
    onSave: (Product) -> Unit
) {
    var name by remember { mutableStateOf(sandwich?.name ?: "") }
    var category by remember { mutableStateOf(sandwich?.category ?: ProductCategory.BEEF) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(
                            Product(
                                id = sandwich?.id ?: 0,
                                name = name,
                                category = category
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
        title = { Text(text = if (sandwich == null) "Ajouter un sandwich" else "Modifier le sandwich") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nom") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                DropdownMenuProductCategory(
                    selectedCategory = category,
                    onCategoryChange = { category = it }
                )
            }
        }
    )
}
