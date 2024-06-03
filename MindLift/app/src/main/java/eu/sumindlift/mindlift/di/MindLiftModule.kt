package eu.sumindlift.mindlift.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eu.sumindlift.mindlift.data.dao.CopingStrategyDao
import eu.sumindlift.mindlift.data.dao.EnergyLevelRecordDao
import eu.sumindlift.mindlift.data.db.MindLiftDatabase
import eu.sumindlift.mindlift.data.repository.CopingStrategyRepository
import eu.sumindlift.mindlift.data.repository.EnergyLevelRecordRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MindLiftModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MindLiftDatabase {
        return Room.databaseBuilder(
            appContext,
            MindLiftDatabase::class.java,
            "mindlift"
        ).build()
    }
    @Provides
    @Singleton
    fun provideEnergyLevelRecordDao(appDatabase: MindLiftDatabase) : EnergyLevelRecordDao {
        return appDatabase.energyLevelRecordDao()
    }
    @Provides
    @Singleton
    fun provideEnergyLevelRecordRepository(energyLevelRecordDao: EnergyLevelRecordDao): EnergyLevelRecordRepository {
        return EnergyLevelRecordRepository(energyLevelRecordDao)
    }

    @Provides
    @Singleton
    fun provideCopingStrategyDao(appDatabase: MindLiftDatabase) : CopingStrategyDao {
        return appDatabase.copingStrategyDao()
    }
    @Provides
    @Singleton
    fun provideCopingStrategyRepository(copingStrategyDao: CopingStrategyDao): CopingStrategyRepository {
        return CopingStrategyRepository(copingStrategyDao)
    }
}