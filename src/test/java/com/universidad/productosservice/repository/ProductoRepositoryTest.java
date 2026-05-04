package com.universidad.productosservice.repository;

import com.universidad.productosservice.domain.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll();
    }

    @Test
    void save_asignaIdAutomaticamente() {
        Producto guardado = productoRepository
                .save(new Producto(null, "Laptop", 1500.0, 10));
        
        assertNotNull(guardado.getId());
        assertTrue(guardado.getId() > 0);
    }

    @Test
    void findById_existente_retornaProducto() {
        Producto guardado = productoRepository
                .save(new Producto(null, "Mouse", 50.0, 100));
        
        Optional<Producto> resultado =
                productoRepository.findById(guardado.getId());
        
        assertTrue(resultado.isPresent());
        assertEquals("Mouse", resultado.get().getNombre());
    }

    @Test
    void findAll_retornaListaCompleta() {
        productoRepository.save(new Producto(null, "Teclado", 80.0, 50));
        productoRepository.save(new Producto(null, "Monitor", 350.0, 20));
        
        List<Producto> productos = productoRepository.findAll();
        
        assertEquals(2, productos.size());
    }

    @Test
    void deleteById_eliminaProducto() {
        Producto guardado = productoRepository
                .save(new Producto(null, "Webcam", 90.0, 15));
        
        productoRepository.deleteById(guardado.getId());
        
        assertFalse(productoRepository.findById(guardado.getId()).isPresent());
    }

    @Test
    void findByNombre_encuentraProducto() {
        productoRepository.save(new Producto(null, "iPhone 15", 999.0, 5));
        
        List<Producto> resultado = productoRepository.findByNombre("iPhone 15");
        
        assertEquals(1, resultado.size());
        assertEquals("iPhone 15", resultado.get(0).getNombre());
    }

    @Test
    void findByPrecioGreaterThan_filtraCorrectamente() {
        productoRepository.save(new Producto(null, "Producto Barato", 50.0, 10));
        productoRepository.save(new Producto(null, "Producto Caro", 5000.0, 2));
        
        List<Producto> resultado = productoRepository.findByPrecioGreaterThan(1000.0);
        
        assertEquals(1, resultado.size());
        assertEquals("Producto Caro", resultado.get(0).getNombre());
    }

    @Test
    void findByStockGreaterThan_filtraCorrectamente() {
        productoRepository.save(new Producto(null, "Bajo stock", 100.0, 2));
        productoRepository.save(new Producto(null, "Alto stock", 100.0, 100));
        
        List<Producto> resultado = productoRepository.findByStockGreaterThan(50);
        
        assertEquals(1, resultado.size());
        assertEquals("Alto stock", resultado.get(0).getNombre());
    }
}
