package com.platzi.market.domain.repository;

import com.platzi.market.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    //el parámetro es que cantidad validamos para q sean escasos
    Optional<List<Product>> getScarseProducts(int quantity);
    Optional<Product> getProduct(int productId);
    Product save(Product product);
    void delete(int productId);

}
