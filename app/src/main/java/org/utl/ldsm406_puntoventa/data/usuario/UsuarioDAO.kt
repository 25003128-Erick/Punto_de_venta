package org.utl.ldsm406_puntoventa.data.usuario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDAO{
    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT*FROM usuarios WHERE username = :username AND password = :password")
    suspend fun autenticarUsuario(username:String, password:String): Usuario?
}