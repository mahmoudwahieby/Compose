package com.my.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.my.compose.basics.Basics
import com.my.compose.conversation.Conversation
import com.my.compose.layouts.Layouts
import com.my.compose.ui.theme.ComposeTheme

class MainActivity : ComponentActivity() {
    // @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            // val windowSizeClass = calculateWindowSizeClass(this)
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Conversation(SampleData.conversationSample)
                    // Basics()
                    // Layouts(windowSize = windowSizeClass)
                }
            }
        }
    }
}
