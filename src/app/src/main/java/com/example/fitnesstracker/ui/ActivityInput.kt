package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.model.ActivityType
import com.example.fitnesstracker.data.model.Intensity

const val CREATE_NEW_ACTIVITY_OPTION = "Neue Aktivität erstellen..."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityInput(
    modifier: Modifier = Modifier,
    activityTypes: List<ActivityType>,
    onAddActivity: (String, Int, Intensity) -> Unit,
    onCreateNewActivity: () -> Unit,
    onDeleteActivityType: (ActivityType) -> Unit
) {
    var activityName by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("60") }
    var intensity by remember { mutableStateOf(Intensity.MEDIUM) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text("Aktivität hinzufügen")

        ExposedDropdownMenuBox(
            expanded = isDropdownExpanded,
            onExpandedChange = { isDropdownExpanded = !isDropdownExpanded }
        ) {
            TextField(
                value = activityName,
                onValueChange = { activityName = it },
                label = { Text("Aktivität") },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded) }
            )
            ExposedDropdownMenu(expanded = isDropdownExpanded, onDismissRequest = { isDropdownExpanded = false }) {
                activityTypes.forEach { activityType ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(activityType.name, modifier = Modifier.weight(1f))
                                IconButton(onClick = { 
                                    onDeleteActivityType(activityType)
                                    isDropdownExpanded = false 
                                }) {
                                    Icon(Icons.Default.Close, contentDescription = "Delete ${activityType.name}", modifier = Modifier.size(18.dp))
                                }
                            }
                        },
                        onClick = {
                            activityName = activityType.name
                            isDropdownExpanded = false
                        },
                        // Prevent the whole item from closing the menu when the icon is clicked
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                DropdownMenuItem(
                    text = { Text(CREATE_NEW_ACTIVITY_OPTION) },
                    onClick = {
                        onCreateNewActivity()
                        isDropdownExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Dauer in Minuten") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Intensität: ${intensity.name}")
        Slider(
            value = intensity.ordinal.toFloat(),
            onValueChange = { intensity = Intensity.values()[it.toInt()] },
            valueRange = 0f..Intensity.values().size.toFloat() - 1,
            steps = Intensity.values().size - 2
        )

        Button(
            onClick = { onAddActivity(activityName, duration.toIntOrNull() ?: 0, intensity) },
            modifier = Modifier.align(Alignment.End),
            enabled = activityName.isNotBlank() && activityName != CREATE_NEW_ACTIVITY_OPTION
        ) {
            Text("Hinzufügen")
        }
    }
}
