package org.aghnyap.omni.android.host.groovyapp

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

@HiltAndroidApp
class GroovyApp : MultiDexApplication() {
    private lateinit var flutterEngine : FlutterEngine

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(APP_ENGINE_ID, flutterEngine)
    }

    companion object {
        const val APP_ENGINE_ID = "groovy_app_engine_id"
    }
}
