package com.spring.sample.controller;

import com.spring.sample.dto.ProductDto;
import com.spring.sample.mapper.MapperHelper;
import com.spring.sample.model.Product;
import com.spring.sample.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/product")
@OpenAPIDefinition(info = @Info(title = "Sample API", version = "1.0", description = "The API Description"))
@CrossOrigin(origins = {"*"}, exposedHeaders = {"X-Total-Count"})
public class ProductController {

    @Autowired
    private MapperHelper<ProductDto, Product> mapperHelperDtoToEntity;

    @Autowired
    private MapperHelper<Product, ProductDto> mapperHelper;

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<Product> getProducts(
            @And({
                    @Spec(path = "id", params = "id", spec = Equal.class),
                    @Spec(path = "sku", params = "sku", spec = Equal.class),
                    // Add more specifications as needed
            })
            Specification<Product> specification, Pageable pageable) {
        return productService.getProducts(specification, pageable);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody ProductDto productDto) {
        Product product = mapperHelperDtoToEntity.map(productDto, Product.class);
        Product response = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public Product editProduct(@PathVariable("id") Long productId,
                               @RequestBody ProductDto requestProduct) {
        Product product = mapperHelperDtoToEntity.map(requestProduct, Product.class);
        return productService.editProduct(productId, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
