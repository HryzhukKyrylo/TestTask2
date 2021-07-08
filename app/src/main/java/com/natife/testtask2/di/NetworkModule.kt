package com.natife.testtask2.di

//@Module
//@InstallIn(SingletonComponent::class)
//class NetworkModule {
//
//    @Singleton
//    @Provides
//    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        return loggingInterceptor
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
//        Retrofit.Builder()
//            .client(okHttpClient)
//            .baseUrl(BuildConfig.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
//        OkHttpClient.Builder()
//            .addNetworkInterceptor(loggingInterceptor)
//            .build()
//
//    @Provides
//    @Singleton
//    fun provideRetrofitService(retrofit: Retrofit): ApiService = retrofit.create(
//        ApiService::class.java
//    )
//
//}
