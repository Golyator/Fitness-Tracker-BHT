package com.example.fitnesstracker.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.fitnesstracker.data.model.ActivityType

@Composable
fun DeleteActivityTypeDialog(
    activityType: ActivityType,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Aktivität löschen") },
        text = { Text("Möchtest du die Aktivität \"${activityType.name}\" wirklich dauerhaft löschen?") },
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
