package com.ultimo_estudio.harold_estudiar.controllers;

import com.ultimo_estudio.harold_estudiar.models.Product;
import com.ultimo_estudio.harold_estudiar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        try{
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.CREATED);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value= "/product")
    public ResponseEntity listProduct(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @GetMapping(value="/product/{id}")
    public ResponseEntity listProductID(@RequestParam Long id){
        Optional<Product> products = productRepository.findById(id);
        if(products.isPresent()){
            return new ResponseEntity(products, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping(value = "/product/nombre/{nombre}")
    public ResponseEntity listProductNombre(@PathVariable String nombre){
        List<Product> products = productRepository.findAllByNombre(nombre);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @GetMapping(value = "/product/{nombre}/{precio}")
    public ResponseEntity listProductNombreID(@PathVariable String nombre,
                                              @PathVariable double precio){
        List<Product> products = productRepository.findAllByNombreAndPrecio(nombre, precio);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(products, HttpStatus.OK);
    }
    @PutMapping(value = "/product/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> productBD =productRepository.findById(id);
        if (productBD.isPresent()){
            try{
                productBD.get().setNombre((product.getNombre()));
                productBD.get().setDescripcion((product.getDescripcion()));
                productBD.get().setCategoria((product.getCategoria()));
                productBD.get().setPrecio((product.getPrecio()));
                productRepository.save((productBD.get()));
                return new ResponseEntity(productBD, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity deleteProduct( @PathVariable Long id){
        Optional<Product> productBD= productRepository.findById(id);
        if(productBD.isPresent()){
            productRepository.delete(productBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }
}
