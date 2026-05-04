package com.universidad.productosservice.service;

import com.universidad.productosservice.domain.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarTodos();
    Optional<Producto> buscarPorId(Long id);
    Producto crear(String nombre, Double precio, Integer stock);
    Producto actualizar(Long id, String nombre, Double precio, Integer stock);
    void eliminar(Long id);
}
