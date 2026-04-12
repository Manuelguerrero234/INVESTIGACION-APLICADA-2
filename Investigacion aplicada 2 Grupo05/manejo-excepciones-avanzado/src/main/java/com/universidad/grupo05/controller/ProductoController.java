package com.universidad.grupo05.controller;

import com.universidad.grupo05.dto.ProductoRequestDTO;
import com.universidad.grupo05.entity.Producto;
import com.universidad.grupo05.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // POST: Crea el producto en la base de datos
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoRequestDTO productoDto) {
        Producto nuevoProducto = productoService.crear(productoDto);
        return ResponseEntity.ok(nuevoProducto);
    }

    // GET: Busca por el ID numérico que generó la base de datos
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }
}