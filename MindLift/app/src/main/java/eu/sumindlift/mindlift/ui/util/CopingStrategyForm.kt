package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.CopingStrategy
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.ui.viewmodel.CopingStrategyFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CopingStrategyForm(
    modifier: Modifier = Modifier,
    viewModel: CopingStrategyFormViewModel = hiltViewModel(),
    initialCopingStrategy: CopingStrategy? = CopingStrategy(),
    onSave: (CopingStrategy) -> Boolean
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var title by rememberSaveable { mutableStateOf(initialCopingStrategy?.title ?: "") }
        var description by rememberSaveable { mutableStateOf(initialCopingStrategy?.description ?: "") }
        var energyLevel by rememberSaveable { mutableStateOf(initialCopingStrategy?.energyLevel ?: EnergyLevel.LOW.getId()) }
        var showSnackbar by remember { mutableStateOf(false) }
        var expanded by remember { mutableStateOf(false) }

        if (showSnackbar) {
            Snackbar(
                action = {
                    TextButton(onClick = { showSnackbar = false }) {
                        Text("OK")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Item saved successfully")
            }
        }

        Text(
            fontWeight = FontWeight.Bold,
            text = stringResource(id = R.string.add_strategy),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = stringResource(id = R.string.add_strat_title))
        TextField(
            value = title,
            onValueChange = { title = it },
            isError = title.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = stringResource(id = R.string.descr))
        TextField(
            value = description,
            minLines = 5,
            maxLines = 10,
            onValueChange = { description = it },
            isError = description.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = stringResource(id = R.string.energy_level))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = it
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                value = stringResource(EnergyLevel.fromId(energyLevel).getTitleResourceId()),
                onValueChange = { },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                EnergyLevel.entries.forEach { selectableEnergyLevel ->
                    DropdownMenuItem(
                        onClick = {
                            energyLevel = selectableEnergyLevel.getId()
                            expanded = false
                        },
                        text = {
                            Text(text = stringResource(selectableEnergyLevel.getTitleResourceId()))
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            var message by remember { mutableStateOf("") }

            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        val newCopingStrategy = CopingStrategy(
                            id = initialCopingStrategy?.id,
                            title = title,
                            description = description,
                            energyLevel = energyLevel
                        )
                        val success = onSave(newCopingStrategy)
                        message = if (success) "Item saved successfully" else "Error saving item"
                        showSnackbar = true
                        title = ""
                        description = ""
                        energyLevel = EnergyLevel.LOW.getId()
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddCopingStrategyPreview() {
//    AddCopingStrategy(initialCopingStrategy = null, onSave = null)
//}