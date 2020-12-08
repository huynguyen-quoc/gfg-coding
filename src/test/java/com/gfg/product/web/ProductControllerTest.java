package com.gfg.product.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfg.product.model.Product;
import com.gfg.product.service.ProductService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {ProductController.class})
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void findAll() throws Exception {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    List<Product> listProduct = new ArrayList<>();
    listProduct.add(product);
    when(productService.list()).thenReturn(listProduct);
    this.mockMvc.perform(get("/products")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value(product.getTitle()))
        .andExpect(jsonPath("$[0].id").value(product.getId()))
        .andExpect(jsonPath("$[0].description").value(product.getDescription()))
        .andExpect(jsonPath("$[0].color").value(product.getColor()))
        .andExpect(jsonPath("$[0].productId").value(product.getProductId()))
        .andExpect(jsonPath("$[0].price").value(product.getPrice()));
  }

  @Test
  void findById() throws Exception {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .id(1L).price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    when(productService.findById(1L)).thenReturn(product);
    this.mockMvc.perform(get("/products/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(product.getTitle()))
        .andExpect(jsonPath("$.id").value(product.getId()))
        .andExpect(jsonPath("$.description").value(product.getDescription()))
        .andExpect(jsonPath("$.color").value(product.getColor()))
        .andExpect(jsonPath("$.productId").value(product.getProductId()))
        .andExpect(jsonPath("$.price").value(product.getPrice()));
  }

  @Test
  void createProduct() throws Exception {
    Product product = Product.builder().productId("test_product_id").brand("test_brand")
        .price(BigDecimal.valueOf(10000L)).color("black").title("test_title")
        .description("test_description")
        .build();
    String jsonBody = objectMapper.writeValueAsString(product);
    when(productService.create(any())).thenReturn(product);
    this.mockMvc.perform(post("/products")
        .content(jsonBody)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isCreated())
        .andExpect(jsonPath("$.title").value(product.getTitle()))
        .andExpect(jsonPath("$.id").value(product.getId()))
        .andExpect(jsonPath("$.description").value(product.getDescription()))
        .andExpect(jsonPath("$.color").value(product.getColor()))
        .andExpect(jsonPath("$.productId").value(product.getProductId()))
        .andExpect(jsonPath("$.price").value(product.getPrice()));
  }

  @Test
  void updateProduct() throws Exception {
    Product product = Product.builder().productId("test_product_id_updated").brand("test_brand_updated")
        .price(BigDecimal.valueOf(10000L)).color("black").title("test_title_updated")
        .description("test_description_updated")
        .build();
    String jsonBody = objectMapper.writeValueAsString(product);
    when(productService.update(any())).thenReturn(product);
    this.mockMvc.perform(put("/products/1")
        .content(jsonBody)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(product.getTitle()))
        .andExpect(jsonPath("$.id").value(product.getId()))
        .andExpect(jsonPath("$.description").value(product.getDescription()))
        .andExpect(jsonPath("$.color").value(product.getColor()))
        .andExpect(jsonPath("$.productId").value(product.getProductId()))
        .andExpect(jsonPath("$.price").value(product.getPrice()));
  }

  @Test
  void deleteProduct() throws Exception {
    doNothing().when(productService).delete(1L);
    this.mockMvc.perform(delete("/products/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isNoContent());
  }
}