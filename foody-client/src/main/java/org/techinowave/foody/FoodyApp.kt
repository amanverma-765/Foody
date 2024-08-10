package org.techinowave.foody

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.techinowave.data.koin.localModule
import org.techinowave.data.koin.remoteModule
import org.techinowave.data.koin.repositoryModule
import org.techinowave.domain.koin.useCaseModule
import org.techinowave.foody.koin.presentationModule


class FoodyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodyApp)
            androidLogger()
            modules(
                remoteModule,
                repositoryModule,
                useCaseModule,
                presentationModule,
                localModule
            )
        }

    }
}