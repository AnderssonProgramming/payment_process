package edu.eci.cvds.payment_process;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio para acceder a la colección de productos en MongoDB.
 */
@Repository
public interface ProductoRepository extends MongoRepository<Producto, String> {
    
    /**
     * Busca un producto por su id.
     * @param idProducto identificador del producto
     * @return el producto encontrado o null si no existe.
     */
    Producto findByIdProducto(String idProducto);
}


