package com.example.rickandmorty.ui.bottomnavigation.episodes

import com.example.rickandmorty.ui.bottomnavigation.episodes.data.EpisodesApiService
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.datasource.EpisodesDataSource
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo.EpisodesRepository
import com.example.rickandmorty.ui.bottomnavigation.episodes.data.repo.EpisodesRepositoryImpl
import com.example.rickandmorty.ui.bottomnavigation.episodes.domain.GetEpisodes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class EpisodesModule {

    @Singleton
    @Provides
    fun provideEpisodeRepository(dataSource: EpisodesDataSource): EpisodesRepository {
        return EpisodesRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun provideEpisodesApiService(retrofit: Retrofit): EpisodesApiService =
        retrofit.create(EpisodesApiService::class.java)

    @Singleton
    @Provides
    fun provideEpisodesDataSource(apiService: EpisodesApiService): EpisodesDataSource {
        return EpisodesDataSource(apiService)
    }
    @Singleton
    @Provides
    fun provideEpisodesRepository(repository: EpisodesRepository): GetEpisodes {
        return GetEpisodes(repository)
    }
}