package com.effectivem.android.tradebybata

import android.app.Application
import com.effectivem.android.domain.di.domainModule
import com.effectivem.android.data.di.dataModule
import com.effectivem.android.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TradeByBataApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TradeByBataApplication)
            modules(listOf(dataModule, domainModule , presentationModule))
        }
    }
}