package org.utl.ldsm406_puntoventa.views.modulo_register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.utl.ldsm406_puntoventa.data.usuario.Usuario

@Composable
fun RegistroFormView(
    onGuardar: (Usuario) -> Unit,
    onCancelar: () -> Unit
){
    var nombre by rememberSaveable() { mutableStateOf("") }
    var username by rememberSaveable() { mutableStateOf("") }
    var password by rememberSaveable() { mutableStateOf("") }
    var passwordd by rememberSaveable() { mutableStateOf("") }

    var error by rememberSaveable() { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Registro de usuario")

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("Username:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Contraseña:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = passwordd,
            onValueChange = {passwordd = it},
            label = { Text("Confirmar contraseña:") },
            modifier = Modifier.fillMaxWidth()
        )

        if(error.isNotBlank()){
            Text(error)
        }

        Button(
            onClick = {
                error = when{
                    nombre.isBlank() -> "Captura el nombre de usuario"
                    username.isBlank() -> "Captura el username"
                    password.isBlank() -> "Captura la contraseña"
                    passwordd.isBlank() -> "Captura la contraseña"
                    else -> ""
                }
                if (error.isBlank()){
                    if (password.trim() == passwordd.trim()){
                        onGuardar(
                            Usuario(
                                id = 0,
                                nombre = nombre.trim(),
                                username = username.trim(),
                                password = password.trim()
                            )
                        )
                    }else{
                        error = "¡La contraseña no es igual!"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Registrar")
        }

    }
}