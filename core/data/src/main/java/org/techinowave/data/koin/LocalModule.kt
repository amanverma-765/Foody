package org.techinowave.data.koin

import org.koin.dsl.module
import org.techinowave.data.local.datastore.DataStoreManager

val localModule = module {
    single {
        DataStoreManager(context = get())
    }
}