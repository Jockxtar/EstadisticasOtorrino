@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.jockxtar.estadisticasotorrino

import android.os.Bundle
import android.os.Message
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHost
import coil.compose.AsyncImage
import com.jockxtar.estadisticasotorrino.ui.theme.EstadisticasOtorrinoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", "onCreate: ")
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
    val viewModel = MainViewModel()
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        val uiState = viewModel.uiState.value
        AsyncImage(
            model = uiState,
            contentDescription = null,
            modifier = Modifier.clickable{
                viewModel.cambiaImagen()
            }
        )

    }

    val thickPadding = Modifier.padding(16.dp)

    Scaffold(
        modifier = thickPadding,
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
                //verticalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
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
            /*Image(
                painter = painterResource(id = R.drawable.pepe),
                contentDescription = stringResource(id = R.string.pepe_cd),
            )*/

        }
    }
}

class MainViewModel() : ViewModel(){
    val randomImage
        get() = (1..5).random()
    fun cambiaImagen(){
        _imageState.value = imagenes.get(randomImage)
    }
    val imagenes = listOf<String>(
        "",
        "",
        "",
    )
    private val _imageState = mutableStateOf<String>("")
    val uiState
        get() = _imageState
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
    val imageModifier = Modifier
        .size(75.dp)
        .border(BorderStroke(1.dp, Color.Black))
        //.background(Color.Yellow)

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
            Image(
                painter = painterResource(id = R.drawable.pepe),
                contentDescription = stringResource(id = R.string.pepe_cd),
                contentScale = ContentScale.Crop,
                modifier = imageModifier
            )
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

/*@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pilas_screen") {
        composable("pilas_screen") { Profile(/*...*/) }
        composable("add_pilas") { FriendsList(/*...*/) }
    }
}*/