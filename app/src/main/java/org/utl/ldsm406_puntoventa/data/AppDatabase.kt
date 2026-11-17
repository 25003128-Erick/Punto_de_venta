package org.utl.ldsm406_puntoventa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.utl.ldsm406_puntoventa.data.producto.Producto
import org.utl.ldsm406_puntoventa.data.producto.ProductosDAO
import org.utl.ldsm406_puntoventa.data.usuario.Usuario
import org.utl.ldsm406_puntoventa.data.usuario.UsuarioDAO

@Database(
    entities = [Producto::class, Usuario::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun productoDao(): ProductosDAO
    abstract fun usuarioDao(): UsuarioDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obtenerInstancia(context: Context): AppDatabase{
            return INSTANCE ?:synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "punto_venta_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}