package com.example.fitnesstracker.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fitnesstracker.data.model.FoodType

@Composable
fun DeleteFoodTypeDialog(
    foodType: FoodType,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nahrungsmittel löschen") },
        text = { Text("Möchtest du \"${foodType.name}\" wirklich dauerhaft löschen?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Löschen")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Abbrechen")
            }
        }
    )
}
