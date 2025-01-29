package fr.ensisa.cassier.mcdwastetracker.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {

    // Récupère tous les ingrédients
    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>

    // Récupère un ingrédient spécifique par ID
    @Query("SELECT * FROM Ingredient WHERE id = :id")
    fun getIngredientById(id: Int): Flow<Ingredient?>

    // Insère un nouvel ingrédient (IGNORE en cas de conflit)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIngredient(ingredient: Ingredient): Long

    // Met à jour un ingrédient existant
    @Update
    suspend fun updateIngredient(ingredient: Ingredient): Long

    // Opération upsert (insérer ou mettre à jour)
    @Transaction
    suspend fun upsertIngredient(ingredient: Ingredient): Long {
        val id = insertIngredient(ingredient)
        return if (id == -1L) updateIngredient(ingredient) else id
    }

    // Supprime un ingrédient spécifique
    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    // Supprime tous les ingrédients
    @Query("DELETE FROM Ingredient")
    suspend fun deleteAllIngredients()
}
