package fr.ensisa.cassier.mcdwastetracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.ensisa.cassier.mcdwastetracker.models.enums.Quart
import fr.ensisa.cassier.mcdwastetracker.ui.components.QuartDropdown
import java.time.LocalTime

@Composable
fun TrashScreen(
    currentQuart: Quart?,
    onOpenTrash: (Quart, LocalTime) -> Unit,
    onCloseTrash: (LocalTime) -> Unit,
    onResetTrash: () -> Unit
) {
    var selectedQuart by remember { mutableStateOf(currentQuart) }
    val now = LocalTime.now()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Poubelle") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Sélectionnez le quart de la journée", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.height(8.dp))

                    QuartDropdown(
                        selectedQuart = selectedQuart,
                        onQuartSelected = { selectedQuart = it }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            if (selectedQuart != null) {
                                onOpenTrash(selectedQuart!!, now)
                            }
                        },
                        enabled = selectedQuart != null
                    ) {
                        Text("Ouvrir la poubelle")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { onCloseTrash(now) },
                        enabled = currentQuart != null
                    ) {
                        Text("Fermer la poubelle")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = onResetTrash,
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error)
                    ) {
                        Text("Réinitialiser", color = MaterialTheme.colors.onError)
                    }
                }
            }
        }
    )
}
