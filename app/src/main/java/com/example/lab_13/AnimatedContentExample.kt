package com.example.lab_13

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalAnimationApi::class)  // Para usar AnimatedContent con efectos
@Composable
fun AnimatedContentExample() {
  // Estados para controlar qué mensaje mostrar
  var currentState by remember { mutableStateOf(State.LOADING) }

  // AnimatedContent para realizar las transiciones entre los estados
  AnimatedContent(
    targetState = currentState,
    transitionSpec = {
      // Definir la animación entre los estados (FadeIn / FadeOut)
      if (initialState == State.LOADING && targetState == State.CONTENT) {
        fadeIn(animationSpec = tween(500)) with fadeOut(animationSpec = tween(500))
      } else if (initialState == State.CONTENT && targetState == State.ERROR) {
        fadeIn(animationSpec = tween(700)) with fadeOut(animationSpec = tween(700))
      } else {
        fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
      }
    }
  ) { state ->
    // Mostrar el contenido según el estado
    when (state) {
      State.LOADING -> {
        Text(text = "Cargando...", color = Color.Gray)
      }
      State.CONTENT -> {
        Text(text = "Contenido cargado correctamente!", color = Color.Green)
      }
      State.ERROR -> {
        Text(text = "Ha ocurrido un error", color = Color.Red)
      }
    }
  }

  Spacer(modifier = Modifier.height(16.dp))

  // Botones para cambiar de estado
  Column(
    verticalArrangement = Arrangement.spacedBy(16.dp), // Espaciado vertical entre los botones
    horizontalAlignment = Alignment.CenterHorizontally, // Centrar los botones horizontalmente
    modifier = Modifier
      .fillMaxSize() // Ocupa el tamaño completo de la pantalla
      .wrapContentSize(Alignment.Center) // Centrar la columna en la pantalla
  ) {
    Button(onClick = { currentState = State.LOADING }) {
      Text(text = "Cargar")
    }
    Button(onClick = { currentState = State.CONTENT }) {
      Text(text = "Mostrar Contenido")
    }
    Button(onClick = { currentState = State.ERROR }) {
      Text(text = "Mostrar Error")
    }
  }
}

// Enum para definir los tres estados
enum class State {
  LOADING,
  CONTENT,
  ERROR
}
