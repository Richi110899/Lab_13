package com.example.lab_13

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VisibilityToggle() {
  // Estado para controlar la visibilidad del cuadro
  var isVisible by remember { mutableStateOf(false) }

  // Layout principal
  Column(
    modifier = Modifier
      .fillMaxSize()
      .wrapContentSize(Alignment.Center)
  ) {
    // Botón para alternar visibilidad
    Button(onClick = { isVisible = !isVisible }) {
      Text(text = if (isVisible) "Ocultar Cuadro" else "Mostrar Cuadro")
    }

    // Usando AnimatedVisibility para el cuadro
    AnimatedVisibility(
      visible = isVisible,
      enter = fadeIn(animationSpec = tween(durationMillis = 1000)), // Animación de entrada
      exit = fadeOut(animationSpec = tween(durationMillis = 1000))  // Animación de salida
    ) {
      // Cuadro de color que aparecerá y desaparecerá
      Box(
        modifier = Modifier
          .size(200.dp)
          .background(Color.Blue)
          .padding(16.dp)
      )
    }
  }
}
