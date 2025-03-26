package edu.eci.cvds.payment_process;

import java.util.List;


public interface ProductoService {

    /**
     * Consulta todos los productos.
     * @return lista de productos.
     */
    List<Producto> consultarProductos();

    /**
     * Consulta un producto por su id.
     * @param idProducto identificador del producto.
     * @return el producto encontrado.
     */
    Producto consultarProducto(String idProducto);

    /**
     * Crea un nuevo producto.
     * @param nombre nombre del producto.
     * @param precio precio del producto.
     * @param cantidad cantidad del producto.
     * @param descripcion descripción del producto.
     */
    void crearProducto(String nombre, double precio,int cantidad, String descripcion);

    /**
     * Actualiza un producto existente.
     * @param idProducto identificador del producto.
     * @param productoDTO datos actualizados del producto.
     */
    void actualizarProducto(String idProducto, ProductoDTO productoDTO);

    /**
     * Elimina un producto.
     * @param idProducto identificador del producto.
     */
    void eliminarProducto(String idProducto);
}
