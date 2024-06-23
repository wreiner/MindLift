package eu.sumindlift.mindlift.ui.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import eu.sumindlift.mindlift.R
import eu.sumindlift.mindlift.data.entity.EnergyLevel
import eu.sumindlift.mindlift.ui.viewmodel.AddCopingStrategyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCopingStrategy(
    modifier: Modifier = Modifier,
    viewModel: AddCopingStrategyViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var description by rememberSaveable { mutableStateOf("") }
        var energyLevel by rememberSaveable { mutableStateOf(EnergyLevel.LOW) }
        var expanded by remember { mutableStateOf(false) }

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
        /*Box(modifier = Modifier
            .fillMaxSize()
            .clickable { expanded = true }
        ) {
            Row {
                Text(
                    text = stringResource(id = energyLevel.getTitleResourceId()),

                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                EnergyLevel.entries.forEach {
                    DropdownMenuItem(
                        text = { Text(stringResource(it.getTitleResourceId())) },
                        onClick = {
                            energyLevel = it
                            expanded = false
                        }
                    )
                }
            }
        }*/
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
                value = stringResource(energyLevel.getTitleResourceId()),
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
                            energyLevel = selectableEnergyLevel
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
            Button(
                onClick = {
                    if (title.isNotBlank() && description.isNotBlank()) {
                        viewModel.newCopingStrategy(title, description, energyLevel)
                        title = ""
                        description = ""
                        energyLevel = EnergyLevel.LOW
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.save))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCopingStrategyPreview() {
    AddCopingStrategy()
}