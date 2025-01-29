package fr.ensisa.cassier.mcdwastetracker.data.repository

import androidx.annotation.WorkerThread
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.IngredientDao
import fr.ensisa.cassier.mcdwastetracker.models.Ingredient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class IngredientRepository(
    private val dispatcher: CoroutineDispatcher,
    private val ingredientDao: IngredientDao
) {

    fun getAllIngredients(): Flow<List<Ingredient>> {
        return ingredientDao.getAllIngredients()
    }

    fun getIngredientById(id: Int): Flow<Ingredient?> {
        return ingredientDao.getIngredientById(id)
    }

    @WorkerThread
    suspend fun insertIngredient(ingredient: Ingredient): Long = withContext(dispatcher) {
        return@withContext ingredientDao.insertIngredient(ingredient)
    }

    @WorkerThread
    suspend fun updateIngredient(ingredient: Ingredient): Long = withContext(dispatcher) {
        return@withContext ingredientDao.updateIngredient(ingredient)
    }

    @WorkerThread
    suspend fun upsertIngredient(ingredient: Ingredient): Long = withContext(dispatcher) {
        return@withContext ingredientDao.upsertIngredient(ingredient)
    }

    @WorkerThread
    suspend fun deleteIngredient(ingredient: Ingredient) = withContext(dispatcher) {
        ingredientDao.deleteIngredient(ingredient)
    }
}
