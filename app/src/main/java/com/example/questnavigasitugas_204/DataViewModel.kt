package com.example.questnavigasitugas_204

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ViewModel yang akan menyimpan dan mengelola data formulir
class DataViewModel : ViewModel() {
    // _uiState adalah MutableStateFlow, yang artinya nilainya bisa diubah
    private val _uiState = MutableStateFlow(DataForm())
    // uiState adalah StateFlow (read-only), yang akan diekspos ke UI
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    // Fungsi-fungsi untuk memperbarui setiap field di DataForm
    fun setNama(nama: String) {
        _uiState.update { currentState ->
            currentState.copy(nama = nama)
        }
    }

    fun setJenisKelamin(jenisKelamin: String) {
        _uiState.update { currentState ->
            currentState.copy(jenisKelamin = jenisKelamin)
        }
    }

    fun setStatus(status: String) {
        _uiState.update { currentState ->
            currentState.copy(status = status)
        }
    }

    fun setAlamat(alamat: String) {
        _uiState.update { currentState ->
            currentState.copy(alamat = alamat)
        }
    }

    // Fungsi untuk mereset semua data jika diperlukan
    fun resetForm() {
        _uiState.value = DataForm()
    }
}

// Data class untuk merepresentasikan semua field dalam formulir
data class DataForm(
    val nama: String = "",
    val jenisKelamin: String = "",
    val status: String = "",
    val alamat: String = ""
)