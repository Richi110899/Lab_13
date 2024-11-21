package com.example.lab_13

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
import androidx.compose.animation.animateColorAsState

@Composable
fun ColorChangeExample() {
  // Estado para controlar el color
  var isBlue by remember { mutableStateOf(true) }

  // Usando animateColorAsState para animar el cambio de color
  val backgroundColor by animateColorAsState(
    targetValue = if (isBlue) Color.Blue else Color.Green,
    animationSpec = tween(
      durationMillis = 1000,  // Duración de la animación (1 segundo)
      delayMillis = 0         // Sin retraso
    )
  )

  // Layout principal
  Column(
    modifier = Modifier
      .fillMaxSize()
      .wrapContentSize(Alignment.Center)
  ) {
    // Cuadro que cambiará de color
    Box(
      modifier = Modifier
        .size(200.dp)
        .background(backgroundColor)
        .padding(16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Botón para alternar entre los colores
    Button(onClick = { isBlue = !isBlue }) {
      Text(text = "Cambiar Color")
    }
  }
}
