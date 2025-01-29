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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
fun LossItemCard(
    ingredient: Ingredient,
    quantity: Int,
    onIncrement: (Ingredient) -> Unit,
    onDecrement: (Ingredient) -> Unit,
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
                    painter = painterResource(id = ingredient.image.toInt()), // Assurez-vous de mapper correctement l'ID de l'image
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

            // Nom et catégorie de l'ingrédient
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = ingredient.name,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = ingredient.category.name,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Contrôles pour la quantité
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { onDecrement(ingredient) },
                    enabled = quantity > 0
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Decrement",
                        tint = if (quantity > 0) Color.Black else Color.Gray
                    )
                }

                Text(
                    text = quantity.toString(),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(onClick = { onIncrement(ingredient) }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Increment",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}
