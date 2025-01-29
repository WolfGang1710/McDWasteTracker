package fr.ensisa.cassier.mcdwastetracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.ensisa.cassier.mcdwastetracker.data.database.converters.DateConverter
import fr.ensisa.cassier.mcdwastetracker.data.database.converters.EnumConverters
import fr.ensisa.cassier.mcdwastetracker.data.database.converters.TimeConverter
import fr.ensisa.cassier.mcdwastetracker.data.database.converters.TrashItemConverter
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.IngredientDao
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.ProductDao
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.TrashDao
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import fr.ensisa.cassier.mcdwastetracker.models.Product
import fr.ensisa.cassier.mcdwastetracker.models.Trash

@Database(
    entities = [Product::class, Ingredient::class, Trash::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(EnumConverters::class, DateConverter::class, TimeConverter::class, TrashItemConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun trashDao(): TrashDao
}

