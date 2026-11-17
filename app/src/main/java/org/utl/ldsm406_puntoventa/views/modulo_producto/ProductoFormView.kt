package org.utl.ldsm406_puntoventa.views.modulo_producto

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
import org.utl.ldsm406_puntoventa.data.producto.Producto

@Composable
fun ProductoFormView(
    productoEditar: Producto? = null,
    onGuardar: (Producto) -> Unit,
    onCancelar: () -> Unit
){
    var nombre by rememberSaveable() { mutableStateOf("") }
    var descripcion by rememberSaveable() { mutableStateOf("") }
    var precio by rememberSaveable() { mutableStateOf("") }
    var existencia by rememberSaveable() { mutableStateOf("") }
    var categoria by rememberSaveable() { mutableStateOf("") }
    var error by rememberSaveable() { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Registro de producto")

        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it},
            label = { Text("Nombre:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = descripcion,
            onValueChange = {descripcion = it},
            label = { Text("Descripcion:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = precio,
            onValueChange = {precio = it},
            label = { Text("Precio:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = existencia,
            onValueChange = {existencia = it},
            label = { Text("Existencia:") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = categoria,
            onValueChange = {categoria = it},
            label = { Text("Categoria:") },
            modifier = Modifier.fillMaxWidth()
        )

        if(error.isNotBlank()){
            Text(error)
        }

        Button(
            onClick = {
                val precioFloat = precio.toDoubleOrNull()
                val existenciaInt = existencia.toIntOrNull()

                error = when{
                    nombre.isBlank() -> "Captura el nombre del Producto"
                    categoria.isBlank() -> "Captura la categoria del Producto"
                    existenciaInt == null -> "Captura la existencia"
                    precioFloat == null -> "captura un precio correcto"
                    else -> ""
                }
                if (error.isBlank()){
                    onGuardar(
                        Producto(
                            id = 0,
                            nombre = nombre.trim(),
                            descripcion = descripcion.trim(),
                            precio = precioFloat!!,
                            existencia = existenciaInt!!,
                            categoria = categoria.trim()
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Guardar")
        }

    }
}