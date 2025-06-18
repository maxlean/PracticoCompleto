package com.AlkemyJava2TP3.PracticoParte3.service;

import com.AlkemyJava2TP3.PracticoParte3.dto.ProductoDTO;
import com.AlkemyJava2TP3.PracticoParte3.model.Producto;
import com.AlkemyJava2TP3.PracticoParte3.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    public List<Producto> getTodosProductos() {
        return productoRepository.findAll();
    }

    public void crearProducto(ProductoDTO productoDTO) {
        Producto nuevoProducto = new Producto(
                productoDTO.getId(),
                productoDTO.getNombre(),
                productoDTO.getDescripcion(),
                productoDTO.getPrecio(),
                productoDTO.isEnStock()
        );
        productoRepository.save(nuevoProducto);
    }

    public void actualizarProducto(String productoId, ProductoDTO productoDTO){
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if(producto != null){
            producto.setNombre(productoDTO.getNombre());
            producto.setDescripcion(productoDTO.getDescripcion());
            productoRepository.save(producto);
        }
    }

    public void eliminarProducto(String productoId){
        Producto producto = productoRepository.findById(productoId).orElse(null);

        if (producto != null){
            productoRepository.delete(producto);
        }
    }
}
