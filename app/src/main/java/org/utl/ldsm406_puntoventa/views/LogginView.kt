package org.utl.ldsm406_puntoventa.views

import android.graphics.fonts.Font
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.utl.ldsm406_puntoventa.R

@Composable
fun LogginView(
    onIngresar: (String, String) -> Unit,
    onRegister: () -> Unit

){
    var username by remember { mutableStateOf("") }
    var password = rememberTextFieldState()
    var msgError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    colorResource(R.color.carbon_red),
                    colorResource(R.color.medium_red)
                )
            )
        )
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Punto de venta",
            color = colorResource(R.color.red),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,

            )
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .padding(20.dp)//funciona como margin
                .background(colorResource(R.color.medium_red),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp) //funciona como padding
        ) {
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text("Ingresa tu usuario")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(R.color.carbon_red),
                    unfocusedTextColor = colorResource(R.color.carbon),
                    focusedContainerColor = colorResource(R.color.red),
                    unfocusedContainerColor = colorResource(R.color.carbon_red),
                    focusedLabelColor = colorResource(R.color.carbon_red),
                    unfocusedLabelColor = colorResource(R.color.medium_red)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedSecureTextField(
                state = password,
                label = {
                    Text("Ingresa tu contraseña")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colorResource(R.color.carbon_red),
                    unfocusedTextColor = colorResource(R.color.carbon),
                    focusedContainerColor = colorResource(R.color.red),
                    unfocusedContainerColor = colorResource(R.color.carbon_red),
                    focusedLabelColor = colorResource(R.color.carbon_red),
                    unfocusedLabelColor = colorResource(R.color.medium_red)
                )
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (msgError.isNotBlank()){
                Text(
                    text=msgError,
                    color = Color.Red
                )
            }

            Button(onClick = {
                val textoPassword = password.text.toString().trim()

                if (username.isBlank() || textoPassword.isBlank()){
                    msgError = "Completa los campos"
                }else{
                    msgError = ""
                    onIngresar(username, textoPassword)
                }
            },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    colorResource(R.color.carbon_red),
                    colorResource(R.color.white)
                )
                ) {
                Text("Ingresar")
            }

            Button(onClick = {
                onRegister()
            },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    colorResource(R.color.carbon_red),
                    colorResource(R.color.white)
                ),
            ) {
                Text("Crear usuario")
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogginView(){
    LogginView({user, pass ->}, {})
}