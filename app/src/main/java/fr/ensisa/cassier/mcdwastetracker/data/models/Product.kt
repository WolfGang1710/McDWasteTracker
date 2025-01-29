package fr.ensisa.cassier.mcdwastetracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.ensisa.cassier.mcdwastetracker.models.enums.ProductCategory

@Entity(tableName = "Product")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val category: ProductCategory,
    val image: String? = null // URL ou URI locale
)
