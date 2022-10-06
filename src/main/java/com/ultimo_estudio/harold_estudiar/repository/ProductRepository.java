package com.ultimo_estudio.harold_estudiar.repository;

import com.ultimo_estudio.harold_estudiar.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNombre(String nombre);

    List<Product> findByNombreAndPrecio(String nombre, double precio);
}

