package fr.ensisa.cassier.mcdwastetracker.data.database.converters

import androidx.room.TypeConverter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimeConverter {
    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun fromTime(time: LocalTime?): String? {
        return time?.format(formatter)
    }

    @TypeConverter
    fun toTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it, formatter) }
    }
}
