package com.example.fitnesstracker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.data.model.FoodType
import com.example.fitnesstracker.data.model.PortionSize

const val CREATE_NEW_FOOD_OPTION = "Neues Nahrungsmittel erstellen..."

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodInput(
    modifier: Modifier = Modifier,
    foodTypes: List<FoodType>,
    onAddFood: (String, PortionSize) -> Unit,
    onCreateNewFood: () -> Unit,
    onDeleteFoodType: (FoodType) -> Unit
) {
    var foodName by remember { mutableStateOf("") }
    var portionSize by remember { mutableStateOf(PortionSize.MEDIUM) }
    var isFoodDropdownExpanded by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxWidth()) {
        Text("Nahrungsmittel hinzufügen")

        // Food Type Dropdown
        ExposedDropdownMenuBox(
            expanded = isFoodDropdownExpanded,
            onExpandedChange = { isFoodDropdownExpanded = !isFoodDropdownExpanded }
        ) {
            TextField(
                value = foodName,
                onValueChange = { },
                label = { Text("Nahrungsmittel") },
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isFoodDropdownExpanded) }
            )
            ExposedDropdownMenu(expanded = isFoodDropdownExpanded, onDismissRequest = { isFoodDropdownExpanded = false }) {
                foodTypes.forEach { foodType ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(foodType.name, modifier = Modifier.weight(1f))
                                IconButton(onClick = { 
                                    onDeleteFoodType(foodType)
                                    isFoodDropdownExpanded = false 
                                }) {
                                    Icon(Icons.Default.Close, contentDescription = "Delete ${foodType.name}", modifier = Modifier.size(18.dp))
                                }
                            }
                        },
                        onClick = {
                            foodName = foodType.name
                            isFoodDropdownExpanded = false
                        }
                    )
                }
                DropdownMenuItem(
                    text = { Text(CREATE_NEW_FOOD_OPTION) },
                    onClick = {
                        onCreateNewFood()
                        isFoodDropdownExpanded = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Portion Size Slider
        Text("Portionsgröße: ${portionSize.name}")
        Slider(
            value = portionSize.ordinal.toFloat(),
            onValueChange = { portionSize = PortionSize.values()[it.toInt()] },
            valueRange = 0f..PortionSize.values().size.toFloat() - 1,
            steps = PortionSize.values().size - 2
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { onAddFood(foodName, portionSize) },
            modifier = Modifier.align(Alignment.End),
            enabled = foodName.isNotBlank() && foodName != CREATE_NEW_FOOD_OPTION
        ) {
            Text("Hinzufügen")
        }
    }
}
