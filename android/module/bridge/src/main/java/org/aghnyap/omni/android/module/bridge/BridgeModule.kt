package org.aghnyap.omni.android.module.bridge

import android.app.Application
import android.content.Intent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BridgeModule {
    private const val APP_ENGINE_ID = "groovy_app_engine_id"

    @Provides
    @Singleton
    fun provideFlutterEngine(application: Application): FlutterEngineCache {
        // Instantiate a FlutterEngine.
        val flutterEngine = FlutterEngine(application)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        return FlutterEngineCache
            .getInstance().also { cache ->
                cache.put(APP_ENGINE_ID, flutterEngine)
            }
    }

    @Provides
    @Singleton
    @Named("flutter")
    fun provideFlutterActivityIntent(
        application: Application,
        flutterEngineCache: FlutterEngineCache
    ): Intent {
        return if (flutterEngineCache.contains(APP_ENGINE_ID)) {
            FlutterActivity.withCachedEngine(APP_ENGINE_ID).build(application)
        } else {
            FlutterActivity.withNewEngine().build(application)
        }
    }
}