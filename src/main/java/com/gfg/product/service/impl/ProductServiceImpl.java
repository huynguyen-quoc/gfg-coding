package com.gfg.product.service.impl;

import com.gfg.product.exception.BusinessException;
import com.gfg.product.exception.InternalServerErrorException;
import com.gfg.product.exception.NotFoundException;
import com.gfg.product.model.Product;
import com.gfg.product.repository.ProductRepository;
import com.gfg.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Override
  @Transactional
  public Product update(Product product) throws BusinessException {
    log.info("received update request with data=[{}]", product);
    Optional<Product> existingProduct = repository.findById(product.getId());

    if (existingProduct.isEmpty()) {
      log.error("product id=[{}] is does not existed", product.getId());
      throw new NotFoundException("Product Not Found with Id " + product.getId());
    }
    Product currentProduct = existingProduct.get();
    if (product.getProductId() != null && !product.getProductId().isBlank()) {
      currentProduct.setProductId(product.getProductId());
    }
    if (product.getBrand() != null && !product.getBrand().isBlank()) {
      currentProduct.setBrand(product.getBrand());
    }
    if (product.getColor() != null && !product.getColor().isBlank()) {
      currentProduct.setColor(product.getColor());
    }
    if (product.getTitle() != null && !product.getTitle().isBlank()) {
      currentProduct.setTitle(product.getTitle());
    }
    if (product.getDescription() != null && !product.getDescription().isBlank()) {
      currentProduct.setDescription(product.getDescription());
    }
    if (product.getPrice() != null) {
      currentProduct.setPrice(product.getPrice());
    }
    return currentProduct;
  }

  @Override
  public Product findById(Long id) throws BusinessException {
    log.info("received find product request with id=[{}]", id);
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Product Not Found with Id " + id));
  }

  @Override
  public List<Product> list() throws BusinessException {
    log.info("received list product request");
    List<Product> productList = new ArrayList<>();

    repository.findAll().iterator().forEachRemaining(productList::add);
    if (productList.isEmpty()) {
      log.warn("list product is return empty result");
      throw new NotFoundException("No products found");
    }
    log.info("return list product with size=[{}]", productList.size());
    return productList;
  }

  @Override
  public Product create(Product product) throws BusinessException {
    log.info("received create product request with data=[{}]", product);
    try {
      return repository.save(product);
    } catch (Exception ex) {
      log.error("create product failed with error=[{}]", ex.getMessage());
      throw new BusinessException(ex.getMessage());
    }
  }

  @Override
  public void delete(Long id) throws BusinessException {
    log.info("received delete product request with id=[{}]", id);
    try {
      repository.deleteById(id);
    } catch (Exception ex) {
      log.error("delete request failed with error=[{}]", ex.getMessage());
      throw new InternalServerErrorException("delete product failed");
    }
  }
}
