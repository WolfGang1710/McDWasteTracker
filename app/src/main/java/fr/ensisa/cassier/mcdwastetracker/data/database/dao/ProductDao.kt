package fr.ensisa.cassier.mcdwastetracker.data.database.dao

import androidx.room.*
import fr.ensisa.cassier.mcdwastetracker.models.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    fun getAllProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :id")
    fun getProductById(id: Int): Flow<Product?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product): Long

    @Update
    suspend fun updateProduct(product: Product): Long

    @Transaction
    suspend fun upsertProduct(product: Product): Long {
        val id = insertProduct(product)
        return if (id == -1L) updateProduct(product) else id
    }

    @Delete
    suspend fun deleteProduct(product: Product)
}

