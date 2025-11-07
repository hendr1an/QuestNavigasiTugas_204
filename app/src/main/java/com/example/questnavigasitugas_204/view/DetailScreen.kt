package com.example.questnavigasitugas_204.view



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.questnavigasitugas_204.DataViewModel
import com.example.questnavigasitugas_204.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    dataViewModel: DataViewModel = viewModel(),
    onBerandaClicked: () -> Unit,
    onFormulirClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Ambil UI state dari ViewModel
    val uiState by dataViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.list_daftar_peserta),
                        color = MaterialTheme.colorScheme.onPrimary // <- Teks di atas primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
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
            // Tampilkan data dalam Card
            DataCard(label = stringResource(id = R.string.nama_lengkap), value = uiState.nama)
            DataCard(label = stringResource(id = R.string.jenis_kelamin), value = uiState.jenisKelamin)
            DataCard(label = stringResource(id = R.string.status_perkawinan), value = uiState.status)
            DataCard(label = stringResource(id = R.string.alamat), value = uiState.alamat)

            Spacer(modifier = Modifier.weight(1f)) // Mendorong tombol ke bawah

            Button(
                onClick = onBerandaClicked,
                modifier = Modifier.fillMaxWidth()
                )
             {
                Text(stringResource(id = R.string.beranda))
            }
            Button(
                onClick = onFormulirClicked,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary, // <- Warna secondary
                    contentColor = MaterialTheme.colorScheme.onSecondary // Warna ungu lebih terang
                )
            ) {
                Text(stringResource(id = R.string.formulir_pendaftaran))
            }
        }
    }
}

// Composable kecil untuk menampilkan setiap item data dalam kartu
@Composable
fun DataCard(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif // Menggunakan font SansSerif agar lebih rapi
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(onBerandaClicked = {}, onFormulirClicked = {})
}