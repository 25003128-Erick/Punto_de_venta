package org.utl.ldsm406_puntoventa.views.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.utl.ldsm406_puntoventa.data.usuario.UsuarioViewModel
import org.utl.ldsm406_puntoventa.views.HomeView
import org.utl.ldsm406_puntoventa.views.LogginView
import org.utl.ldsm406_puntoventa.views.modulo_register.RegistroFormView

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login") {
            val viewModel: UsuarioViewModel = viewModel()
            LogginView(
                onIngresar = {user, pass ->
                    viewModel.autenticar(user, pass){ valido->
                        if (valido){
                            navController.navigate("home"){
                                popUpTo("login"){ inclusive=true }
                            }
                        }else{

                        }
                    }
                },
                onRegister = {
                    navController.navigate("formRegistro")
                }
            )
        }
        composable("formRegistro") {
        val viewModel: UsuarioViewModel = viewModel()
            RegistroFormView(
                onGuardar = { usuario ->
                    viewModel.guardar(usuario){
                        navController.popBackStack()
                    }
                },
                onCancelar = {
                    navController.popBackStack()
                }
            )

        }
        composable("home") {
            HomeView()
        }
    }
}