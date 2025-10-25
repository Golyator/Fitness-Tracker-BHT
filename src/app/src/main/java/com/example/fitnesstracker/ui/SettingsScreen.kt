package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.Gender
import com.example.fitnesstracker.data.UserSettings
import com.example.fitnesstracker.data.calculateBmr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    userSettings: UserSettings,
    onSave: (UserSettings) -> Unit,
    onNavigateBack: () -> Unit
) {
    var age by remember(userSettings.age) { mutableStateOf(userSettings.age.toString()) }
    var height by remember(userSettings.height) { mutableStateOf(userSettings.height.toString()) }
    var weight by remember(userSettings.weight) { mutableStateOf(userSettings.weight.toString()) }
    var gender by remember(userSettings.gender) { mutableStateOf(userSettings.gender) }

    val currentSettings = UserSettings(
        age = age.toIntOrNull() ?: 0,
        height = height.toIntOrNull() ?: 0,
        weight = weight.toDoubleOrNull() ?: 0.0,
        gender = gender
    )
    val bmr = currentSettings.calculateBmr()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Einstellungen") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = age, onValueChange = { age = it }, label = { Text("Alter") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = height, onValueChange = { height = it }, label = { Text("Größe (cm)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = weight, onValueChange = { weight = it }, label = { Text("Gewicht (kg)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(16.dp))

            Text("Geschlecht")
            Row(Modifier.fillMaxWidth()) {
                Gender.values().forEach { genderOption ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = (gender == genderOption),
                            onClick = { gender = genderOption }
                        )
                        Text(text = genderOption.name, modifier = Modifier.padding(start = 4.dp, end = 16.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Geschätzter Grundumsatz (BMR): $bmr kcal / Tag", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { onSave(currentSettings) }, modifier = Modifier.fillMaxWidth()) {
                Text("Speichern")
            }
        }
    }
}
