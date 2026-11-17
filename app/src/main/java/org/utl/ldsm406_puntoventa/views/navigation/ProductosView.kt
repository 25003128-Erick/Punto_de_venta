package org.utl.ldsm406_puntoventa.views.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.utl.ldsm406_puntoventa.R
import org.utl.ldsm406_puntoventa.data.producto.Producto
import org.utl.ldsm406_puntoventa.data.producto.ProductoViewModel
import org.utl.ldsm406_puntoventa.views.modulo_producto.ProductoFormView

@Composable
fun ProductosView(
    productoViewModel: ProductoViewModel = viewModel()
){
    val productos by productoViewModel.productos.collectAsState()

    var mostrarFormulario by remember { mutableStateOf(false) }
    var productoEditar by remember { mutableStateOf<Producto?>(null) }

    if( mostrarFormulario){
        ProductoFormView(
            productoEditar = productoEditar,
            onGuardar = { producto ->
                productoViewModel.guardar(producto){
                    mostrarFormulario = false
                    productoEditar = null
                }
            },
            onCancelar = {
                mostrarFormulario = false
                productoEditar = null
            }
        )
    }
    else{
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Productos Registrados")
            Button(
                onClick = {
                    productoEditar = null
                    mostrarFormulario = true
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Text("Agregar Producto")
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = productos,
                    key = { it.id }
                ){ producto ->
                    Card(modifier = Modifier.padding(12.dp)) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(producto.nombre)
                            Text("Categoria: ${producto.categoria}")
                            Text("Precio: ${producto.precio}")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewProductos(){
    ProductosView()
}