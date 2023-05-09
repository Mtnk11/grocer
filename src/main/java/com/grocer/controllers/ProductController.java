package com.grocer.controllers;

import com.grocer.models.Product;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.ProductDTO;
import com.grocer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll()
                .stream()
                .map(ProductDTO::map)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable("id") int id){
       Product product = productService.findById(id);

        return ProductDTO.map(product);
    }
    @PostMapping("/add")
    public Integer save(@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
        return productDTO.getId();
    }

    @PostMapping("/upload")
    public void upload(FileUpload productsToUpload) throws IOException {
        productService.upload(productsToUpload);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody ProductDTO productDTO) {
        productService.update(id,productDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")int id) {
        productService.delete(id);
    }
}