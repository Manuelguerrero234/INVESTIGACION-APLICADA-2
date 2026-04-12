package com.universidad.grupo05.repository;

import com.universidad.grupo05.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar producto por código
    Optional<Producto> findByCodigo(String codigo);

    // Verificar si ya existe un código
    boolean existsByCodigo(String codigo);
}