package com.ultimo_estudio.harold_estudiar.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="productos")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @Column(length = 100, nullable = false)
    private String categoria;
    @Column(length = 1000, nullable = false)
    private double precio;
}
