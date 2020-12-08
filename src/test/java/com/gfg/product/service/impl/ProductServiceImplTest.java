package com.gfg.product.service.impl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.gfg.product.exception.BusinessException;
import com.gfg.product.exception.InternalServerErrorException;
import com.gfg.product.exception.NotFoundException;
import com.gfg.product.model.Product;
import com.gfg.product.repository.ProductRepository;
import com.gfg.product.service.ProductService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productService = new ProductServiceImpl(productRepository);
  }

  @AfterEach
  void tearDown() {
    productService = null;
  }

  @Test
  void update() {
    Product currentProduct = Product.builder().productId("test_product_id_current")
        .brand("test_brand_current")
        .id(1L).price(BigDecimal.valueOf(10010L)).color("black").title("test_title_current")
        .description("test_description_current")
        .build();
    Product updateProduct = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productRepository.findById(any())).thenReturn(Optional.of(currentProduct));

    Product result = productService.update(updateProduct);

    assertThat(result).isEqualTo(updateProduct);
    Mockito.verify(productRepository, times(1)).findById(any());
  }

  @Test
  void updateWithProductIsNotExistedShouldThrowNotFound() {
    Product updateProduct = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productRepository.findById(any())).thenReturn(Optional.empty());

    assertThatThrownBy(() ->
        productService.update(updateProduct)).isInstanceOf(
        NotFoundException.class).hasMessage("Product Not Found with Id 1");
    Mockito.verify(productRepository, times(1)).findById(1L);
  }

  @Test
  void findById() {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    Product result = productService.findById(1L);

    assertThat(result).isEqualTo(product);
    Mockito.verify(productRepository, times(1)).findById(1L);
  }

  @Test
  void findByIdWithEmptyResultShouldThrowNotFound() {
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    assertThatThrownBy(() ->
        productService.findById(1L)).isInstanceOf(
        NotFoundException.class).hasMessage("Product Not Found with Id 1");
    Mockito.verify(productRepository, times(1)).findById(1L);
  }

  @Test
  void list() {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    List<Product> listProduct = new ArrayList<>();
    listProduct.add(product);
    when(productRepository.findAll()).thenReturn(listProduct);

    List<Product> result = productService.list();

    assertThat(result).hasSize(1);
    assertThat(result.get(0)).isEqualTo(product);
    Mockito.verify(productRepository, times(1)).findAll();
  }

  @Test
  void listWithEmptyResultShouldThrowNotFound() {
    List<Product> listProduct = new ArrayList<>();
    when(productRepository.findAll()).thenReturn(listProduct);

    assertThatThrownBy(() ->
        productService.list()).isInstanceOf(
        NotFoundException.class).hasMessage("No products found");
    Mockito.verify(productRepository, times(1)).findAll();
  }

  @Test
  void create() {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productRepository.save(any())).thenReturn(product);

    Product result = productService.create(product);

    assertThat(result).isEqualTo(product);
    Mockito.verify(productRepository, times(1)).save(any());
  }

  @Test
  void createWithRunExceptionThrowShouldThrowException() {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productRepository.save(any())).thenThrow(new RuntimeException("some errors"));

    assertThatThrownBy(() ->
        productService.create(product)).isInstanceOf(
        BusinessException.class).hasMessage("some errors");
    Mockito.verify(productRepository, times(1)).save(any());
  }

  @Test
  void delete() {
    doNothing().when(productRepository).deleteById(1L);

    productService.delete(1L);
    Mockito.verify(productRepository, times(1)).deleteById(1L);
  }

  @Test
  void deleteWithRunExceptionThrowShouldThrowException() {
    doThrow(new RuntimeException("some errors")).when(productRepository).deleteById(1L);

    assertThatThrownBy(() ->
        productService.delete(1L)).isInstanceOf(
        InternalServerErrorException.class).hasMessage("delete product failed");
    Mockito.verify(productRepository, times(1)).deleteById(1L);
  }
}