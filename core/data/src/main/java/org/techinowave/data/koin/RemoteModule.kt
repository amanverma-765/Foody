package org.techinowave.data.koin

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.techinowave.data.BuildConfig
import org.techinowave.data.remote.supabase.SupabaseDataSource

val remoteModule = module {

    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = BuildConfig.supabaseUrl,
            supabaseKey = BuildConfig.supabaseApiKey
        ) {
            defaultSerializer = KotlinXSerializer(
                Json {
                    ignoreUnknownKeys = true
//                    explicitNulls = false
                }
            )
            install(Postgrest)
        }
    }

    single { SupabaseDataSource(supabaseClient = get()) }
}