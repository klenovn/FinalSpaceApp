package com.klenovn.finalspaceapp.di

import android.app.Application
import androidx.room.Room
import com.klenovn.finalspaceapp.data.local.FinalSpaceDao
import com.klenovn.finalspaceapp.data.local.FinalSpaceDatabase
import com.klenovn.finalspaceapp.data.remote.FinalSpaceApi
import com.klenovn.finalspaceapp.data.repository.CharacterRepositoryImpl
import com.klenovn.finalspaceapp.domain.repository.CharacterRepository
import com.klenovn.finalspaceapp.domain.use_case.character.GetAllCharactersUseCase
import com.klenovn.finalspaceapp.domain.use_case.character.GetCharacterUseCase
import com.klenovn.finalspaceapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFinalSpaceApi(): FinalSpaceApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.FINAL_SPACE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FinalSpaceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFinalSpaceDatabase(app: Application): FinalSpaceDatabase {
        return Room.databaseBuilder(
            app,
            FinalSpaceDatabase::class.java,
            "finalspacedb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFinalSpaceDao(db: FinalSpaceDatabase): FinalSpaceDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(
        api: FinalSpaceApi,
        dao: FinalSpaceDao
    ): CharacterRepository {
        return CharacterRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(
        repository: CharacterRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetCharacterUseCase(
        repository: CharacterRepository
    ): GetCharacterUseCase {
        return GetCharacterUseCase(repository)
    }
}
