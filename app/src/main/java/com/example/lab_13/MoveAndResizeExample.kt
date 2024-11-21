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
import androidx.compose.animation.core.animateDpAsState

@Composable
fun MoveAndResizeExample() {
  // Estado para controlar si el cuadro está en su posición inicial o no
  var isMoved by remember { mutableStateOf(false) }
  var isResized by remember { mutableStateOf(false) }

  // Animación para el tamaño del cuadro
  val boxSize by animateDpAsState(
    targetValue = if (isResized) 200.dp else 100.dp,  // Cambia el tamaño
    animationSpec = tween(durationMillis = 1000)  // Duración de la animación
  )

  // Animación para la posición del cuadro
  val offsetX by animateDpAsState(
    targetValue = if (isMoved) 100.dp else 0.dp,  // Cambia la posición
    animationSpec = tween(durationMillis = 1000)
  )

  // Layout principal
  Column(
    modifier = Modifier
      .fillMaxSize()
      .wrapContentSize(Alignment.Center)
  ) {
    // Cuadro que cambiará de tamaño y posición
    Box(
      modifier = Modifier
        .size(boxSize)  // Aplica el tamaño animado
        .offset(x = offsetX)  // Aplica la posición animada
        .background(Color.Blue)
        .padding(16.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Botón para mover y cambiar el tamaño del cuadro
    Button(onClick = {
      isMoved = !isMoved  // Alterna la posición
      isResized = !isResized  // Alterna el tamaño
    }) {
      Text(text = "Mover y Cambiar Tamaño")
    }
  }
}
