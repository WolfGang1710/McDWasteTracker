package fr.ensisa.cassier.mcdwastetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.ensisa.cassier.mcdwastetracker.models.Trash
import kotlinx.coroutines.flow.Flow

@Dao
interface TrashDao {

    // Récupère toutes les poubelles
    @Query("SELECT * FROM Trash")
    fun getAllTrash(): Flow<List<Trash>>

    // Récupère une poubelle spécifique par ID
    @Query("SELECT * FROM Trash WHERE id = :id")
    fun getTrashById(id: Int): Flow<Trash?>

    // Récupère les poubelles pour une date donnée
    @Query("SELECT * FROM Trash WHERE date = :date")
    fun getTrashByDate(date: String): Flow<List<Trash>>

    // Insère une nouvelle poubelle (IGNORE en cas de conflit)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTrash(trash: Trash): Long

    // Met à jour une poubelle existante
    @Update
    suspend fun updateTrash(trash: Trash): Long

    // Opération upsert (insérer ou mettre à jour)
    @Transaction
    suspend fun upsertTrash(trash: Trash): Long {
        val id = insertTrash(trash)
        return if (id == -1L) updateTrash(trash) else id
    }

    // Supprime une poubelle spécifique
    @Delete
    suspend fun deleteTrash(trash: Trash)

    // Supprime toutes les poubelles
    @Query("DELETE FROM Trash")
    suspend fun deleteAllTrash()
}
