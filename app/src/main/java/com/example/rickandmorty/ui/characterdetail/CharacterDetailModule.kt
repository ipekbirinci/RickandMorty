package com.example.rickandmorty.ui.characterdetail

import com.example.rickandmorty.ui.bottomnavigation.characters.data.CharactersApiService
import com.example.rickandmorty.ui.bottomnavigation.characters.data.datasource.CharactersDataSource
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepositoryImpl
import com.example.rickandmorty.ui.bottomnavigation.characters.domain.GetCharacters
import com.example.rickandmorty.ui.characterdetail.data.CharacterDetailApiService
import com.example.rickandmorty.ui.characterdetail.data.datasource.CharacterDetailDataSource
import com.example.rickandmorty.ui.characterdetail.data.repo.CharacterDetailRepository
import com.example.rickandmorty.ui.characterdetail.data.repo.CharacterDetailRepositoryImpl
import com.example.rickandmorty.ui.characterdetail.domain.GetCharacterDetail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CharacterDetailModule {

    @Singleton
    @Provides
    fun provideCharacterDetailRepository(dataSource: CharacterDetailDataSource): CharacterDetailRepository {
        return CharacterDetailRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideCharacterDetailApiService(retrofit: Retrofit): CharacterDetailApiService =
        retrofit.create(CharacterDetailApiService::class.java)

    @Singleton
    @Provides
    fun provideCharacterDetailDataSource(apiService: CharacterDetailApiService): CharacterDetailDataSource {
        return CharacterDetailDataSource(apiService)
    }
    @Singleton
    @Provides
    fun provideGetCharacterDetail(repository: CharacterDetailRepository): GetCharacterDetail {
        return GetCharacterDetail(repository)
    }
}

