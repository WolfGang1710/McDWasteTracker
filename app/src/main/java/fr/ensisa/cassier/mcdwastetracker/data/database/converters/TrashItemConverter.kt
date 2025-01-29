package fr.ensisa.cassier.mcdwastetracker.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.google.gson.Gson
import fr.ensisa.cassier.mcdwastetracker.models.TrashItem

object TrashItemConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromTrashItemList(items: List<TrashItem>): String {
        return gson.toJson(items)
    }

    @TypeConverter
    fun toTrashItemList(itemsJson: String): List<TrashItem> {
        val type = object : TypeToken<List<TrashItem>>() {}.type
        return gson.fromJson(itemsJson, type)
    }
}
