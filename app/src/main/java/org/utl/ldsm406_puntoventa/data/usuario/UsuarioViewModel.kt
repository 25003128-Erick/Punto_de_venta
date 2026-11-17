package org.utl.ldsm406_puntoventa.data.usuario

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.utl.ldsm406_puntoventa.data.AppDatabase

class UsuarioViewModel(
    application: Application
): AndroidViewModel(application) {

    private val dao = AppDatabase
        .obtenerInstancia(application)
        .usuarioDao()

    private val repository = UsuarioRepository(dao)

    fun guardar(usuario: Usuario, alGuardar: () -> Unit){
        viewModelScope.launch {
            repository.insertar(usuario)
            alGuardar()
        }
    }

    fun autenticar(username:String, password:String, onResultado: (Boolean) -> Unit){
        viewModelScope.launch {
            val usuario = repository.autenticarUsuario(username, password)
            onResultado(usuario != null)
        }
    }

}