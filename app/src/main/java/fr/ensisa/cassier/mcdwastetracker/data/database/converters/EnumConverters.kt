package fr.ensisa.cassier.mcdwastetracker.data.database.converters

import androidx.room.TypeConverter
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.IngredientSubCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.ProductCategory
import fr.ensisa.cassier.mcdwastetracker.models.enums.UnitType

object EnumConverters {

    @TypeConverter
    fun fromIngredientCategory(value: IngredientCategory): String = value.name

    @TypeConverter
    fun toIngredientCategory(value: String): IngredientCategory = IngredientCategory.valueOf(value)

    @TypeConverter
    fun fromIngredientSubCategory(value: IngredientSubCategory?): String? = value?.name

    @TypeConverter
    fun toIngredientSubCategory(value: String?): IngredientSubCategory? =
        value?.let { IngredientSubCategory.valueOf(it) }

    @TypeConverter
    fun fromUnitType(value: UnitType?): String? = value?.name

    @TypeConverter
    fun toUnitType(value: String?): UnitType? = value?.let { UnitType.valueOf(it) }

    @TypeConverter
    fun fromProductCategory(value: ProductCategory): String = value.name

    @TypeConverter
    fun toProductCategory(value: String): ProductCategory = ProductCategory.valueOf(value)
}
