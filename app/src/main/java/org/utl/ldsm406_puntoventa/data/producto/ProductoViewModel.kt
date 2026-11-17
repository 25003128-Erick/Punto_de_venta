package org.utl.ldsm406_puntoventa.data.producto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.utl.ldsm406_puntoventa.data.AppDatabase

class ProductoViewModel(
    application: Application
): AndroidViewModel(application) {

    private val dao = AppDatabase
        .obtenerInstancia(application)
        .productoDao()

    private val repository = ProductoRepository(dao)

    val productos = repository.obtenerTodos().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun guardar(producto: Producto, alGuardar: () -> Unit){
        viewModelScope.launch {
            repository.insertar(producto)
            alGuardar()
        }
    }

}