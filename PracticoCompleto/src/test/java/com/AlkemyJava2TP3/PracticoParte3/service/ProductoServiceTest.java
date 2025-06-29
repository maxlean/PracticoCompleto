package com.AlkemyJava2TP3.PracticoParte3.service;

import com.AlkemyJava2TP3.PracticoParte3.dto.ProductoDTO;
import com.AlkemyJava2TP3.PracticoParte3.model.Producto;
import com.AlkemyJava2TP3.PracticoParte3.repository.ProductoRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Tag("PruebasUnitariasService")
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private ProductoService productoService;

    @Test
    void getTodosProductos() {
        List<Producto> productos = List.of(new Producto("1", "Producto1", "Descripcion1", 100.0, true));
        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> resultado = productoService.getTodosProductos();

        assertEquals(1, resultado.size());
        assertEquals("Producto1", resultado.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void getTodosProductosVacio() {
        when(productoRepository.findAll()).thenReturn(List.of());

        List<Producto> resultado = productoService.getTodosProductos();

        assertTrue(resultado.isEmpty());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void crearProducto() {
        ProductoDTO productoDTO = new ProductoDTO("1", "Producto1", "Descripcion1", 100.0, true);

        productoService.crearProducto(productoDTO);

        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void actualizarProductoSiExiste() {
        Producto productoExistente = new Producto("1", "Producto1", "Descripcion1", 100.0, true);
        ProductoDTO productoDTO = new ProductoDTO("1", "ProductoActualizado", "DescripcionActualizada", 150.0, true);
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoExistente));

        productoService.actualizarProducto("1", productoDTO);

        assertEquals("ProductoActualizado", productoExistente.getNombre());
        assertEquals("DescripcionActualizada", productoExistente.getDescripcion());
        verify(productoRepository, times(1)).save(productoExistente);
    }

    @Test
    void actualizarProductoSiNoExiste() {
        ProductoDTO productoDTO = new ProductoDTO("1", "ProductoActualizado", "DescripcionActualizada", 150.0, true);
        when(productoRepository.findById("1")).thenReturn(Optional.empty());

        productoService.actualizarProducto("1", productoDTO);

        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    void eliminarProductoSiExiste() {
        Producto productoExistente = new Producto("1", "Producto1", "Descripcion1", 100.0, true);
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoExistente));

        productoService.eliminarProducto("1");

        verify(productoRepository, times(1)).delete(productoExistente);
    }

    @Test
    void eliminarProductoSiNoExiste() {
        when(productoRepository.findById("1")).thenReturn(Optional.empty());

        productoService.eliminarProducto("1");

        verify(productoRepository, never()).delete(any(Producto.class));
    }
}