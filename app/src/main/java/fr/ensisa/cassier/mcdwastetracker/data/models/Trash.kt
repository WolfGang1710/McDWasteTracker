package fr.ensisa.cassier.mcdwastetracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import fr.ensisa.cassier.mcdwastetracker.data.database.converters.TrashItemConverter
import fr.ensisa.cassier.mcdwastetracker.models.enums.Quart
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "Trash")
@TypeConverters(TrashItemConverter::class)
data class Trash(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDate,
    val timeOpen: LocalTime,
    val timeClose: LocalTime? = null,
    val quart: Quart,
    val items: List<TrashItem> = emptyList() // Liste des déchets
)

data class TrashItem(
    val id: Int,
    val name: String,
    val quantity: Int
)
