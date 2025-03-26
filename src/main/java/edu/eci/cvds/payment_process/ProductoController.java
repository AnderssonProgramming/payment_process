package edu.eci.cvds.payment_process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "http://localhost:3000") // <--- Permite peticiones desde React
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    /**
     * Consulta todos los productos.
     * @return ResponseEntity con la lista de productos.
     */
    @GetMapping("")
    @Operation(summary = "Consultar productos", description = "Endpoint para consultar todos los productos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos retornados correctamente"),
        @ApiResponse(responseCode = "404", description = "Productos no encontrados")
    })
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoService.consultarProductos());
    }

    /**
     * Consulta un producto por su identificador.
     * @param idProducto identificador del producto.
     * @return ResponseEntity con el producto o error 404 si no se encuentra.
     */
    @GetMapping("{idProducto}/producto")
    @Operation(summary = "Consultar producto", description = "Endpoint para consultar un producto por su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto retornado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> consultarProducto(@PathVariable String idProducto) {
        Producto producto = productoService.consultarProducto(idProducto);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Crea un nuevo producto.
     * @param productoDTO datos del producto.
     * @return ResponseEntity con mensaje de éxito.
     */
    @PostMapping("")
    @Operation(summary = "Crear producto", description = "Endpoint para crear un producto.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto creado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos del producto incorrectos")
    })
    public ResponseEntity<String> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        productoService.crearProducto(productoDTO.getNombre(), productoDTO.getPrecio(),productoDTO.getCantidad(), productoDTO.getDescripcion());
        return ResponseEntity.ok("Producto creado");
    }

    /**
     * Actualiza un producto existente.
     * @param idProducto identificador del producto.
     * @param productoDTO datos actualizados del producto.
     * @return ResponseEntity sin contenido.
     */
    @PatchMapping("/{idProducto}")
    @Operation(summary = "Actualizar producto", description = "Endpoint para actualizar un producto por su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Void> actualizarProducto(@PathVariable String idProducto, @RequestBody ProductoDTO productoDTO) {
        productoService.actualizarProducto(idProducto, productoDTO);
        return ResponseEntity.ok().build();
    }

    /**
     * Elimina un producto.
     * @param idProducto identificador del producto.
     * @return ResponseEntity con mensaje de éxito.
     */
    @DeleteMapping("/{idProducto}")
    @Operation(summary = "Eliminar producto", description = "Endpoint para eliminar un producto por su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<String> eliminarProducto(@PathVariable String idProducto) {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.ok("Producto eliminado");
    }
}
