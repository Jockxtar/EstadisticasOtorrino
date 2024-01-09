@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.jockxtar.estadisticasotorrino

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jockxtar.estadisticasotorrino.ui.theme.EstadisticasOtorrinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EstadisticasOtorrinoTheme {
                MainActivityContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivityContent() {
    Box {
        val modifierPadding = Modifier.padding(8.dp)


        Column {

            Scaffold(
                topBar = {
                    TopAppBar(
                        /*colors = topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        ),*/
                        title = {
                            Text("Top app bar")
                        }
                    )
                },
                bottomBar = {
                    BottomAppBar(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.primary,
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Bottom app bar",
                        )
                    }
                }
            )
            { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Text(text = "Hola Mundo")

                    LazyColumn {
                        item {
                            Text("Lista de pacientes")
                        }
                        // Add a single item
                        item {
                            PacienteItem(Paciente("Pepe", 20, "Otitis"))
                        }

                        // Add 5 items
                        items(3) { index ->
                            PacienteItem(Paciente("José", 56, "Vértigo"))
                        }

                        // Add another single item
                        item {
                            Text(text = "Last item")
                        }
                    }
                    Image(
                            painter = painterResource(id = R.drawable.pepe),
                    contentDescription = stringResource(id = R.string.pepe_cd),
                    )

                }
            }
        }
    }
}

@Composable
fun Paciente(paciente: Paciente) {
    Column {
        Text(text = "Nombre: ${paciente.nombre}")
        Text(text = "Edad: ${paciente.edad}")
        Text(text = "Enfermedad: ${paciente.enfermedad}")
    }
}

class Paciente(val nombre: String, val edad: Int, val enfermedad: String)

@Composable
fun PacientesList(pacientes: List<Paciente>) {
    LazyColumn {
        items(pacientes) { paciente ->
            PacienteItem(paciente)
        }
    }
}

@Composable
fun PacienteItem(paciente: Paciente) {
    Column {
        Row {
            /*Image(
                painter = painterResource(id = R.drawable.pepe),
                contentDescription = stringResource(id = R.string.pepe_cd),
            )*/
        }
        Row {
            Text(paciente.nombre + ", ")
            Text(paciente.edad.toString() + ", ")
            Text(paciente.enfermedad)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EstadisticasOtorrinoTheme {
        MainActivityContent()
    }
}

