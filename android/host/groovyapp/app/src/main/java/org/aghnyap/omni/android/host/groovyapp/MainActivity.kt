package org.aghnyap.omni.android.host.groovyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.android.FlutterActivity
import org.aghnyap.omni.android.host.groovyapp.GroovyApp.Companion.APP_ENGINE_ID
import org.aghnyap.omni.android.host.groovyapp.ui.theme.GroovyAppTheme
import org.aghnyap.omni.android.module.greeting.Greeting

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroovyAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Flutter", onClick = {
                        startActivity(
                            FlutterActivity
                                .withCachedEngine(APP_ENGINE_ID)
                                .build(this)
                        )
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GroovyAppTheme {
        Greeting("Flutter")
    }
}