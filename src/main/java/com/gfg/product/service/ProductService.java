package com.gfg.product.service;

import com.gfg.product.exception.BusinessException;
import com.gfg.product.model.Product;
import java.util.List;

public interface ProductService {

  Product update(Product product) throws BusinessException;

  Product findById(Long id) throws BusinessException;

  List<Product> list() throws BusinessException;

  Product create(Product product) throws BusinessException;

  void delete(Long id) throws BusinessException;

}
