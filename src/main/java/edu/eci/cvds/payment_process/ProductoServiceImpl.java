package edu.eci.cvds.payment_process;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> consultarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto consultarProducto(String idProducto) {
        return productoRepository.findByIdProducto(idProducto);
    }

    @Override
    public void crearProducto(String nombre, double precio, int cantidad, String descripcion) {
        Producto producto = new Producto(nombre, precio,cantidad, descripcion);
        productoRepository.save(producto);
    }

    @Override
    public void actualizarProducto(String idProducto, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findByIdProducto(idProducto);
        if (producto != null) {
            if (productoDTO.getNombre() != null) {
                producto.setNombre(productoDTO.getNombre());
            }
            if (productoDTO.getPrecio() != 0) {
                producto.setPrecio(productoDTO.getPrecio());
            }
            if (productoDTO.getCantidad() != 0) {
                producto.setCantidad(productoDTO.getCantidad());
            }
            if (productoDTO.getDescripcion() != null) {
                producto.setDescripcion(productoDTO.getDescripcion());
            }
            productoRepository.save(producto);
        }
    }

    @Override
    public void eliminarProducto(String idProducto) {
        Producto producto = productoRepository.findByIdProducto(idProducto);
        if (producto != null) {
            productoRepository.delete(producto);
        }
    }
}
