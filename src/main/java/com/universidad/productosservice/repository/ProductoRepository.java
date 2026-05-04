package com.universidad.productosservice.repository;

import com.universidad.productosservice.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);
    List<Producto> findByPrecioGreaterThan(Double precio);
    List<Producto> findByStockGreaterThan(Integer stock);
}
