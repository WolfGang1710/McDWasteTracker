package fr.ensisa.cassier.mcdwastetracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientSubCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.UnitType

@Entity(tableName = "Ingredient")
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: IngredientCategory,
    val subCategory: IngredientSubCategory? = null,
    val unit: UnitType? = null,
    val image: String? = null // URI ou URL de l'image
)
