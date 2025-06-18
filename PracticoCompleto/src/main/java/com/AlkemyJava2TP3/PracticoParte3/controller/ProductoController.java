package com.AlkemyJava2TP3.PracticoParte3.controller;

import com.AlkemyJava2TP3.PracticoParte3.dto.ProductoDTO;
import com.AlkemyJava2TP3.PracticoParte3.model.Producto;
import com.AlkemyJava2TP3.PracticoParte3.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return new ResponseEntity<>(productoService.getTodosProductos(),HttpStatus.CREATED);
    }

    @PostMapping(value="/agregarProducto")
    public ResponseEntity<Void> saveProducto(@RequestBody ProductoDTO productoDTO){
        productoService.crearProducto(productoDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/udpate/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable String id, @RequestBody ProductoDTO productoDTO) {
        productoService.actualizarProducto(id, productoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
