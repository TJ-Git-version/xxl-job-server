package com.felix.xxljobscene.service;

import com.felix.xxljobscene.domain.entity.Product;

import java.util.List;

public interface ProductService {

    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product getById(Long id);

    List<Product> list();
}