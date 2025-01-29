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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Fournit une instance unique de la base de données
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mcd_waste_tracker_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    // Fournit une instance de ProductDao
    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    // Fournit une instance de IngredientDao
    @Provides
    @Singleton
    fun provideIngredientDao(database: AppDatabase): IngredientDao {
        return database.ingredientDao()
    }

    // Fournit une instance de TrashDao
    @Provides
    @Singleton
    fun provideTrashDao(database: AppDatabase): TrashDao {
        return database.trashDao()
    }
}
