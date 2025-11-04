package com.fitlife.app.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

data class Exercise(
    val name: String,
    val description: String,
    val duration: String
)

@Composable
fun HomeScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(true) }
    var exercises by remember { mutableStateOf(listOf<Exercise>()) }

    // Simula la carga desde una API
    LaunchedEffect(Unit) {
        delay(1500)
        exercises = listOf(
            Exercise("Sentadillas", "3 series de 15 repeticiones", "10 min"),
            Exercise("Flexiones", "3 series de 12 repeticiones", "8 min"),
            Exercise("Abdominales", "3 series de 20 repeticiones", "12 min"),
        )
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Plan de Entrenamiento") },
                actions = {
                    TextButton(onClick = { navController.navigate("profile") }) {
                        Text("Perfil")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator()
                }
                exercises.isEmpty() -> {
                    Text("No hay ejercicios disponibles.")
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(exercises) { exercise ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(exercise.name, style = MaterialTheme.typography.titleMedium)
                                    Text(exercise.description)
                                    Text("Duración: ${exercise.duration}", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


