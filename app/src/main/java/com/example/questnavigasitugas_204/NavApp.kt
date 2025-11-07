package com.example.questnavigasitugas_204


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.questnavigasitugas_204.view.DetailScreen
import com.example.questnavigasitugas_204.view.FormScreen
import com.example.questnavigasitugas_204.view.HomeScreen

@Composable
fun NavApp(
    navController: NavHostController = rememberNavController()
) {
    // Buat satu instance ViewModel dan bagikan ke semua layar
    // ViewModel ini akan hidup selama NavHost masih ada di layar
    val viewModel: DataViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Navigasi.Home.name // Aplikasi dimulai dari HomeScreen
    ) {
        // Rute untuk Layar Beranda (Image 2)
        composable(route = Navigasi.Home.name) {
            HomeScreen(
                onSubmitClicked = {
                    navController.navigate(Navigasi.Formulir.name)
                }
            )
        }

        // Rute untuk Layar Formulir (Image 1)
        composable(route = Navigasi.Formulir.name) {
            FormScreen(
                dataViewModel = viewModel, // Kirim ViewModel ke FormScreen
                onSubmitClicked = {
                    // Navigasi ke Detail setelah submit formulir
                    navController.navigate(Navigasi.Detail.name)
                }
            )
        }

        // Rute untuk Layar Detail (Image 3)
        composable(route = Navigasi.Detail.name) {
            DetailScreen(
                dataViewModel = viewModel, // Kirim ViewModel ke DetailScreen
                onBerandaClicked = {
                    // Kembali ke Beranda, hapus semua dari tumpukan kecuali Home
                    navController.popBackStack(Navigasi.Home.name, inclusive = false)
                    viewModel.resetForm() // Reset data formulir saat kembali ke Beranda
                },
                onFormulirClicked = {
                    // Kembali ke Formulir, dengan popBackStack() sederhana
                    navController.popBackStack()
                }
            )
        }
    }
}