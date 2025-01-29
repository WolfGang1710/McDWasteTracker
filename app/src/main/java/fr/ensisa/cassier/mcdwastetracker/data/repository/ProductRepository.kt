package fr.ensisa.cassier.mcdwastetracker.data.repository

import androidx.annotation.WorkerThread
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.ProductDao
import fr.ensisa.cassier.mcdwastetracker.models.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProductRepository(
    private val dispatcher: CoroutineDispatcher,
    private val productDao: ProductDao
) {

    fun getAllProducts(): Flow<List<Product>> {
        return productDao.getAllProducts()
    }

    fun getProductById(id: Int): Flow<Product?> {
        return productDao.getProductById(id)
    }

    @WorkerThread
    suspend fun insertProduct(product: Product): Long = withContext(dispatcher) {
        return@withContext productDao.insertProduct(product)
    }

    @WorkerThread
    suspend fun updateProduct(product: Product): Long = withContext(dispatcher) {
        return@withContext productDao.updateProduct(product)
    }

    @WorkerThread
    suspend fun upsertProduct(product: Product): Long = withContext(dispatcher) {
        return@withContext productDao.upsertProduct(product)
    }

    @WorkerThread
    suspend fun deleteProduct(product: Product) = withContext(dispatcher) {
        productDao.deleteProduct(product)
    }
}
