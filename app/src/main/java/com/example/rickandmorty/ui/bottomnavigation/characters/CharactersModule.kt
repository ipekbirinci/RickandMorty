package com.example.rickandmorty.ui.bottomnavigation.characters

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource.CharactersDataSource
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CharactersModule {

    @Singleton
    @Provides
    fun provideCharactersRepository(dataSource: CharactersDataSource): CharactersRepository {
        return CharactersRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideCharactersApiService(retrofit: Retrofit): CharactersApiService =
        retrofit.create(CharactersApiService::class.java)
}
