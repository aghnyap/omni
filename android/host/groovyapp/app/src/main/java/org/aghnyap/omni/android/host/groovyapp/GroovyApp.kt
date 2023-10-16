package org.aghnyap.omni.android.host.groovyapp

import android.content.SharedPreferences
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import javax.inject.Inject

@HiltAndroidApp
class GroovyApp : MultiDexApplication() {
    private lateinit var flutterEngine : FlutterEngine
    private val CHANNEL = "org.aghnyap.omni.android.sharedprefs"

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Initialize method channel here
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    "getSharedPrefValue" -> {
                        val key = call.argument<String>("key")
                        val value = sharedPreferences.getString(key, null)
                        if (value != null) {
                            result.success(value)
                        } else {
                            result.error("UNAVAILABLE", "Value not found", null)
                        }
                    }
                    "setSharedPrefValue" -> {
                        val key = call.argument<String>("key")
                        val value = call.argument<String>("value")
                        with (sharedPreferences.edit()) {
                            putString(key, value)
                            commit()
                        }
                        result.success(true)
                    }
                    else -> result.notImplemented()
                }
        }

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
