package com.example.myapplicationautonomo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationautonomo1.ui.theme.MyApplicationAutonomo1Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationAutonomo1Theme {
                DonationApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationApp() {
    var currentScreen by remember { mutableStateOf("Inicio") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentScreen) }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        }
    ) { innerPadding ->
        when (currentScreen) {
            "Inicio" -> HomeScreen(
                modifier = Modifier.padding(innerPadding),
                onNavigate = { screen -> currentScreen = screen }
            )
            "Iniciar Sesión" -> LoginScreen(modifier = Modifier.padding(innerPadding))
            "Donación" -> DonationFormScreen(modifier = Modifier.padding(innerPadding))
            "Gracias" -> ThankYouScreen(
                modifier = Modifier.padding(innerPadding),
                onBack = { currentScreen = "Inicio" }
            )
        }
    }
}

@Composable
fun BottomNavigationBar(onTabSelected: (String) -> Unit, currentScreen: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Icono de Inicio") },
            label = { Text("Inicio") },
            selected = currentScreen == "Inicio",
            onClick = { onTabSelected("Inicio") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Icono de Iniciar Sesión") },
            label = { Text("Iniciar Sesión") },
            selected = currentScreen == "Iniciar Sesión",
            onClick = { onTabSelected("Iniciar Sesión") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Icono de Donaciones") },
            label = { Text("Donación") },
            selected = currentScreen == "Donación",
            onClick = { onTabSelected("Donación") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ThumbUp, contentDescription = "Icono de Gracias") },
            label = { Text("Gracias") },
            selected = currentScreen == "Gracias",
            onClick = { onTabSelected("Gracias") }
        )
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier, onNavigate: (String) -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "¡Bienvenido a la Aplicación de Donaciones!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "Icono de la Aplicación",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Esta aplicación te ayuda a hacer donaciones de manera fácil y eficiente. Puedes seguir tus donaciones, gestionar tu perfil y más.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))


        Button(onClick = { onNavigate("Iniciar Sesión") }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onNavigate("Donación") }) {
            Text("Hacer una Donación")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Información de contacto o enlaces importantes
        Text(
            text = "Para soporte, contáctanos en soporte@donationapp.com",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inicia Sesión o Regístrate Aquí", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Nombre de Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {  }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { }) {
            Text("Registrarse")
        }
    }
}

@Composable
fun DonationFormScreen(modifier: Modifier = Modifier) {
    var paymentMethod by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var bank by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Formulario de Donación", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = paymentMethod,
            onValueChange = { paymentMethod = it },
            label = { Text("Método de Pago") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = accountNumber,
            onValueChange = { accountNumber = it },
            label = { Text("Número de Cuenta") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bank,
            onValueChange = { bank = it },
            label = { Text("Banco") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Manejar la lógica de donación aquí */ }) {
            Text("Enviar Donación")
        }
    }
}

@Composable
fun ThankYouScreen(modifier: Modifier = Modifier, onBack: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Gracias por tu Donación!", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tu donación ha sido procesada exitosamente.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onBack() }) {
            Text("Regresar a Inicio")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationAutonomo1Theme {
        DonationApp()
    }
}
