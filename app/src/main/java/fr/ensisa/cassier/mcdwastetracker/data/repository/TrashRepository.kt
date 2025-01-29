package fr.ensisa.cassier.mcdwastetracker.data.repository

import androidx.annotation.WorkerThread
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.TrashDao
import fr.ensisa.cassier.mcdwastetracker.models.Trash
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TrashRepository(
    private val dispatcher: CoroutineDispatcher,
    private val trashDao: TrashDao
) {

    fun getAllTrash(): Flow<List<Trash>> {
        return trashDao.getAllTrash()
    }

    fun getTrashById(id: Int): Flow<Trash?> {
        return trashDao.getTrashById(id)
    }

    fun getTrashByDate(date: String): Flow<List<Trash>> {
        return trashDao.getTrashByDate(date)
    }

    @WorkerThread
    suspend fun insertTrash(trash: Trash): Long = withContext(dispatcher) {
        return@withContext trashDao.insertTrash(trash)
    }

    @WorkerThread
    suspend fun updateTrash(trash: Trash): Long = withContext(dispatcher) {
        return@withContext trashDao.updateTrash(trash)
    }

    @WorkerThread
    suspend fun upsertTrash(trash: Trash): Long = withContext(dispatcher) {
        return@withContext trashDao.upsertTrash(trash)
    }

    @WorkerThread
    suspend fun deleteTrash(trash: Trash) = withContext(dispatcher) {
        trashDao.deleteTrash(trash)
    }
}
