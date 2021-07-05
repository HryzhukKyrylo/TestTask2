package com.natife.testtask2.di

import android.provider.Settings
import com.natife.testtask2.data.repository.GlobalRepository
import com.natife.testtask2.data.repository.LocalRepository
import com.natife.testtask2.data.repository.MainRepositoryDecorator
import com.natife.testtask2.data.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Named("local")
    fun bindLocalRetrofit(local: LocalRepository): GlobalRepository {
        return local
    }

    @Provides
    @Named("remote")
    fun bindRemoteRetrofit(remote: RemoteRepository): GlobalRepository {
        return remote
    }

      @Provides
      fun provideMainRepositoryDecorator(
          @Named("local")local : GlobalRepository,
          @Named("remote")remote: GlobalRepository): GlobalRepository{
          return MainRepositoryDecorator(remote, local)
      }
}
