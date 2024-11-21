package com.example.lab_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab_13.ui.theme.Lab_13Theme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Lab_13Theme() {
        MoveAndResizeExample()
      }
    }
  }
}
