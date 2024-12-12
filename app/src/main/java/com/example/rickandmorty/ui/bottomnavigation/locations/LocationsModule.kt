package com.example.rickandmorty.ui.bottomnavigation.locations

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource.CharactersDataSource
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepositoryImpl
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import com.example.rickandmorty.ui.bottomnavigation.locations.data.LocationsApiService
import com.example.rickandmorty.ui.bottomnavigation.locations.data.datasource.LocationsDataSource
import com.example.rickandmorty.ui.bottomnavigation.locations.data.repo.LocationsRepository
import com.example.rickandmorty.ui.bottomnavigation.locations.data.repo.LocationsRepositoryImpl
import com.example.rickandmorty.ui.bottomnavigation.locations.domain.GetLocations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocationsModule {

    @Singleton
    @Provides
    fun provideLocationsRepository(dataSource: LocationsDataSource): LocationsRepository {
        return LocationsRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideLocationsApiService(retrofit: Retrofit): LocationsApiService =
        retrofit.create(LocationsApiService::class.java)

    @Singleton
    @Provides
    fun provideLocationsDataSource(apiService: LocationsApiService): LocationsDataSource {
        return LocationsDataSource(apiService)
    }
    @Singleton
    @Provides
    fun provideGetLocations(repository: LocationsRepository): GetLocations {
        return GetLocations(repository)
    }
}

