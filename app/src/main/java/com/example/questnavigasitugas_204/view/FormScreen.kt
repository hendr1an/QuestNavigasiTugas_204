package com.example.questnavigasitugas_204.view


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questnavigasitugas_204.DataViewModel
import com.example.questnavigasitugas_204.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    dataViewModel: DataViewModel = viewModel(),
    onSubmitClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Ambil UI state dari ViewModel
    val uiState by dataViewModel.uiState.collectAsState()

    val jenisKelaminOptions = listOf("Laki-laki", "Perempuan")
    val statusPerkawinanOptions = listOf("Janda", "Lajang", "Duda")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.formulir_pendaftaran),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF957DAD) // Warna ungu tua
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // NAMA LENGKAP
            Text(stringResource(id = R.string.nama_lengkap), style = MaterialTheme.typography.bodySmall)
            OutlinedTextField(
                value = uiState.nama,
                onValueChange = { dataViewModel.setNama(it) },
                placeholder = { Text("Isian nama lengkap") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp)) // Spasi tambahan

            // JENIS KELAMIN
            Text(stringResource(id = R.string.jenis_kelamin), style = MaterialTheme.typography.bodySmall)
            Column {
                jenisKelaminOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (uiState.jenisKelamin == option),
                                onClick = { dataViewModel.setJenisKelamin(option) }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (uiState.jenisKelamin == option),
                            onClick = { dataViewModel.setJenisKelamin(option) }
                        )
                        Text(text = option, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // Spasi tambahan

            // STATUS PERKAWINAN
            Text(stringResource(id = R.string.status_perkawinan), style = MaterialTheme.typography.bodySmall)
            Column {
                statusPerkawinanOptions.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (uiState.status == option),
                                onClick = { dataViewModel.setStatus(option) }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (uiState.status == option),
                            onClick = { dataViewModel.setStatus(option) }
                        )
                        Text(text = option, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // Spasi tambahan

            // ALAMAT
            Text(stringResource(id = R.string.alamat), style = MaterialTheme.typography.bodySmall)
            OutlinedTextField(
                value = uiState.alamat,
                onValueChange = { dataViewModel.setAlamat(it) },
                placeholder = { Text("Alamat") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

            // TOMBOL SUBMIT
            Button(
                onClick = onSubmitClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF957DAD) // Warna ungu tua
                )
            ) {
                Text(stringResource(id = R.string.submit))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    FormScreen(onSubmitClicked = {})
}