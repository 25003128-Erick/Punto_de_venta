package org.utl.ldsm406_puntoventa.data.producto

import kotlinx.coroutines.flow.Flow

class ProductoRepository(
    private val productosDao: ProductosDAO
){
    fun obtenerTodos(): Flow<List<Producto>>{
        return productosDao.obtenerTodos()
    }
    suspend fun obtenerPorId(id: Int): Producto? {
        return productosDao.obtenerPorId(id)
    }
    suspend fun insertar(producto: Producto){
        return productosDao.insertar(producto)
    }
    suspend fun actualizar(producto: Producto){
        return productosDao.actualizar(producto)
    }
    suspend fun eliminar(producto: Producto){
        productosDao.eliminar(producto)
    }
}