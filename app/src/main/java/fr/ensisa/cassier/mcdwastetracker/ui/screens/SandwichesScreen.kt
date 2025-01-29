package fr.ensisa.cassier.mcdwastetracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ensisa.cassier.mcdwastetracker.models.Product
import fr.ensisa.cassier.mcdwastetracker.ui.components.ProductCard

@Composable
fun SandwichesScreen(
    sandwiches: List<Product>,
    onAddSandwich: (Product) -> Unit,
    onEditSandwich: (Product) -> Unit,
    onDeleteSandwich: (Product) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var editingSandwich by remember { mutableStateOf<Product?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sandwichs") },
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Ajouter un sandwich")
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
                if (sandwiches.isEmpty()) {
                    Text(
                        text = "Aucun sandwich disponible.",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn {
                        items(sandwiches) { sandwich ->
                            ProductCard(
                                product = sandwich,
                                onEditClick = {
                                    editingSandwich = sandwich
                                    showDialog = true
                                },
                                onDeleteClick = onDeleteSandwich
                            )
                        }
                    }
                }
            }

            if (showDialog) {
                SandwichDialog(
                    sandwich = editingSandwich,
                    onDismiss = {
                        showDialog = false
                        editingSandwich = null
                    },
                    onSave = {
                        if (editingSandwich == null) {
                            onAddSandwich(it)
                        } else {
                            onEditSandwich(it)
                        }
                        showDialog = false
                        editingSandwich = null
                    }
                )
            }
        }
    )
}
