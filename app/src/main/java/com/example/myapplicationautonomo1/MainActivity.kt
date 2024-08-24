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
    var currentScreen by remember { mutableStateOf("Home") }

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
            "Home" -> HomeScreen(
                modifier = Modifier.padding(innerPadding),
                onNavigate = { screen -> currentScreen = screen }
            )
            "Login" -> LoginScreen(modifier = Modifier.padding(innerPadding))
            "Donation" -> DonationFormScreen(modifier = Modifier.padding(innerPadding))
            "ThankYou" -> ThankYouScreen(
                modifier = Modifier.padding(innerPadding),
                onBack = { currentScreen = "Home" } // Aquí se pasa el lambda para regresar a la pantalla de inicio
            )
        }
    }
}

@Composable
fun BottomNavigationBar(onTabSelected: (String) -> Unit, currentScreen: String) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home Icon") },
            label = { Text("Home") },
            selected = currentScreen == "Home",
            onClick = { onTabSelected("Home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountBox, contentDescription = "Login Icon") },
            label = { Text("Login") },
            selected = currentScreen == "Login",
            onClick = { onTabSelected("Login") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Donations Icon") },
            label = { Text("Donations") },
            selected = currentScreen == "Donation",
            onClick = { onTabSelected("Donation") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ThumbUp, contentDescription = "Thank You Icon") },
            label = { Text("Thank You") },
            selected = currentScreen == "ThankYou",
            onClick = { onTabSelected("ThankYou") }
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
            text = "Welcome to the Donation App!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Puedes agregar una imagen o ícono aquí
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = "App Icon",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "This app helps you make donations easily and efficiently. You can track your donations, manage your profile, and more.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botones para navegar a otras partes de la app
        Button(onClick = { onNavigate("Login") }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { onNavigate("Donation") }) {
            Text("Make a Donation")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Información de contacto o enlaces importantes
        Text(
            text = "For support, contact us at support@donationapp.com",
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
        Text(text = "Login or Register Here", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Handle login logic here */ }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* Handle registration logic here */ }) {
            Text("Register")
        }
    }
}@Composable
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
        Text(text = "Donation Form", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = paymentMethod,
            onValueChange = { paymentMethod = it },
            label = { Text("Payment Method") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = accountNumber,
            onValueChange = { accountNumber = it },
            label = { Text("Account Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bank,
            onValueChange = { bank = it },
            label = { Text("Bank") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { /* Handle donation logic here */ }) {
            Text("Submit Donation")
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
        Text(text = "Thank You for Your Donation!", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your donation has been successfully processed.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onBack() }) {
            Text("Back to Home")
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
