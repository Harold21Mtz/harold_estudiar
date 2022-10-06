package com.ultimo_estudio.harold_estudiar.repository;

import com.ultimo_estudio.harold_estudiar.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
