package fr.ensisa.cassier.mcdwastetracker.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient

@Composable
fun IngredientCard(
    ingredient: Ingredient,
    onEditClick: (Ingredient) -> Unit,
    onDeleteClick: (Ingredient) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image de l'ingrédient
            if (ingredient.image != null) {
                Image(
                    painter = painterResource(id = ingredient.image.toInt()), // Assurez-vous de mapper l'image correctement
                    contentDescription = ingredient.name,
                    modifier = Modifier
                        .size(56.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Placeholder si aucune image n'est fournie
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No Image", fontSize = 10.sp, color = Color.Gray)
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                // Nom de l'ingrédient
                Text(
                    text = ingredient.name,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Catégorie et unité
                Text(
                    text = buildString {
                        append(ingredient.category.name)
                        ingredient.unit?.let {
                            append(" • ${it.name}")
                        }
                    },
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Bouton d'édition
            IconButton(onClick = { onEditClick(ingredient) }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
            }

            // Bouton de suppression
            IconButton(onClick = { onDeleteClick(ingredient) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }
}
