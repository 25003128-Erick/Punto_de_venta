package org.utl.ldsm406_puntoventa.data.usuario

class UsuarioRepository(
    private val usuarioDao: UsuarioDAO
){
    suspend fun insertar(usuario: Usuario){
        return usuarioDao.insertar(usuario)
    }

    suspend fun autenticarUsuario(username:String, password:String):Usuario? {
        return usuarioDao.autenticarUsuario(username, password)
    }
}