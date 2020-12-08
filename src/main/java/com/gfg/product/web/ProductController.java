package com.gfg.product.web;

import com.gfg.product.model.Product;
import com.gfg.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Product>> findAll() {
    return new ResponseEntity<>(productService.list(), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> findById(@PathVariable Long id) {
    return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
    product.setId(id);
    return new ResponseEntity<>(productService.update(product), HttpStatus.OK);

  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    productService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}