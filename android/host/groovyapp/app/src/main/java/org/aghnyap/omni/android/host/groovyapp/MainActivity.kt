package org.aghnyap.omni.android.host.groovyapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.android.FlutterActivity
import org.aghnyap.omni.android.host.groovyapp.GroovyApp.Companion.APP_ENGINE_ID
import org.aghnyap.omni.android.host.groovyapp.ui.theme.GroovyAppTheme
import org.aghnyap.omni.android.module.greeting.Greeting
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counter = remember { mutableStateOf(sharedPreferences.getString("counter", "-1")) }

            val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
                if (key == "counter") {
                    counter.value = sharedPreferences.getString("counter", "-1")
                }
            }

            LaunchedEffect(Unit) {
                sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
            }

            DisposableEffect(Unit) {
                onDispose {
                    sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
                }
            }
            GroovyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(
                        "Flutter ${counter.value}",
                        onClick = {
                            startActivity(
                                FlutterActivity
                                    .withCachedEngine(APP_ENGINE_ID)
                                    .build(this)
                            )
                        },
                        onReset = {
                            with (sharedPreferences.edit()) {
                                putString("counter", "0")
                                apply()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GroovyAppTheme {
        Greeting("Flutter", onClick = {})
    }
}