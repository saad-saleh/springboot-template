package com.spring.sample.service;

import com.spring.sample.exception.CustomException;
import com.spring.sample.exception.ErrorCode;
import com.spring.sample.model.Product;
import com.spring.sample.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getProducts(Specification<Product> specification, Pageable pageable) {
        return productRepository.findAll(specification, pageable);
    }

    public Product getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent())
            throw new CustomException("Product not found",ErrorCode.NOT_FOUND);
        if (product == null) {
            throw new CustomException("Product not found", ErrorCode.NOT_FOUND);
        }
        return product.get();
    }

    public Product createProduct(Product product) {
        Product createdProduct = productRepository.save(product);
        return createdProduct;
    }

    public Product editProduct(Long productId, Product product) {
        logger.info("editProduct>>productId:{}>>requestProduct:{}", productId, product);

        Product retrievedProduct = getProduct(productId);
        // Set other properties as needed
        productRepository.save(retrievedProduct);
        return retrievedProduct;
    }

    public void deleteProduct(Long productId) {
        Product product = getProduct(productId);
        productRepository.delete(product);
    }
}
