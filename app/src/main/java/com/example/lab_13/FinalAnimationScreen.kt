package com.example.lab_13

import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class) // Para usar AnimatedVisibility y AnimatedContent
@Composable
fun FinalAnimationScreen() {
  // Estados para el cambio de color, tamaño, visibilidad y movimiento del cuadro
  var isResized by remember { mutableStateOf(false) }
  var isColorChanged by remember { mutableStateOf(false) }
  var isBoxVisible by remember { mutableStateOf(true) }
  var isDarkMode by remember { mutableStateOf(false) }

  // Animación de tamaño y color
  val boxSize by animateDpAsState(
    targetValue = if (isResized) 200.dp else 100.dp,
    animationSpec = tween(durationMillis = 1000)
  )
  val boxColor by animateColorAsState(
    targetValue = if (isColorChanged) Color.Green else Color.Blue,
    animationSpec = tween(durationMillis = 1000)
  )

  // Transición del contenido (modo claro y oscuro)
  val backgroundColor = if (isDarkMode) Color.Black else Color.White
  val textColor = if (isDarkMode) Color.White else Color.Black

  // Layout principal
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(backgroundColor)
      .padding(16.dp)
  ) {
    // AnimatedVisibility para el cuadro
    AnimatedVisibility(
      visible = isBoxVisible,
      enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
      exit = slideOutHorizontally(targetOffsetX = { it }) + fadeOut(),
    ) {
      Box(
        modifier = Modifier
          .size(boxSize)
          .background(boxColor)
          .align(Alignment.Center) // Coloca el cuadro en el centro
      )
    }

    // Column para alinear los botones verticalmente
    Column(
      modifier = Modifier
        .align(Alignment.Center) // Coloca la columna de botones en el centro de la pantalla
        .padding(vertical = 16.dp), // Separación entre los botones
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Botón para cambiar tamaño y color
      Button(
        onClick = {
          isResized = !isResized
          isColorChanged = !isColorChanged
        },
        modifier = Modifier
          .fillMaxWidth()  // Asegura que el botón tenga un ancho completo
          .padding(horizontal = 32.dp, vertical = 8.dp)  // Da un padding horizontal y vertical para tamaño uniforme
      ) {
        Text(text = "Cambiar tamaño y color")
      }

      // Botón para alternar entre modo claro y oscuro
      Button(
        onClick = {
          isDarkMode = !isDarkMode
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp, vertical = 8.dp)  // Consistente en tamaño de botón
      ) {
        Text(
          text = if (isDarkMode) "Modo Claro" else "Modo Oscuro",
          color = textColor,
          fontWeight = FontWeight.Bold
        )
      }

      // Botón para alternar visibilidad y movimiento del cuadro
      Button(
        onClick = {
          isBoxVisible = !isBoxVisible // Alterna la visibilidad del cuadro
        },
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 32.dp, vertical = 8.dp)  // Mantener la consistencia en el tamaño
      ) {
        Text(text = "Mover y Desaparecer Cuadro")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun PreviewFinalAnimationScreen() {
  FinalAnimationScreen()
}
