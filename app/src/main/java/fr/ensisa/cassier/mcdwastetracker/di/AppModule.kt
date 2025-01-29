package fr.ensisa.cassier.mcdwastetracker.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.ensisa.cassier.mcdwastetracker.data.database.AppDatabase
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.IngredientDao
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.ProductDao
import fr.ensisa.cassier.mcdwastetracker.data.database.dao.TrashDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Fournit un contexte d'application
    @Provides
    @Singleton
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    // Fournit une instance de Room Database
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mcd_waste_tracker_db"
        ).fallbackToDestructiveMigration().build()
    }

    // Fournit un DAO pour les produits
    @Provides
    fun provideProductDao(database: AppDatabase): ProductDao = database.productDao()

    // Fournit un DAO pour les ingrédients
    @Provides
    fun provideIngredientDao(database: AppDatabase): IngredientDao = database.ingredientDao()

    // Fournit un DAO pour les poubelles
    @Provides
    fun provideTrashDao(database: AppDatabase): TrashDao = database.trashDao()

    // Fournit un dispatcher pour les opérations en arrière-plan
    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
