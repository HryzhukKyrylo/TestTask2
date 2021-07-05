package com.natife.testtask2.di

import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.LocalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import com.natife.testtask2.data.repository.RemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Named(LOCAL)
    fun bindLocalRetrofit(local: LocalRepository): GlobalRepository {
        return local
    }

    @Provides
    @Named(REMOTE)
    fun bindRemoteRetrofit(remote: RemoteRepository): GlobalRepository {
        return remote
    }

    @Provides
    fun provideMainRepositoryDecorator(
        @Named(LOCAL) local: GlobalRepository,
        @Named(REMOTE) remote: GlobalRepository
    ): GlobalRepository {
        return MainRepositoryDecorator(remote, local)
    }

    companion object {
        const val LOCAL = "local"
        const val REMOTE = "remote"
    }
}
