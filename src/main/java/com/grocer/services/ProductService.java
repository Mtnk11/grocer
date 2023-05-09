package com.grocer.services;

import com.grocer.models.dto.FileUpload;
import com.grocer.models.dto.ProductDTO;
import com.grocer.models.Product;
import com.grocer.repositories.ProductRepository;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productsRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }
    public List<Product> findAllByIDs(List<Integer> ids) {
        List<Product> products = new ArrayList<>();
        for (Integer id : ids) {
            Product byId = findById(id);
            products.add(byId);
        }
        return products;
    }
    public Product findById(int id) {
        return productsRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product with id '" + id + "' not found"));
    }

    public ProductDTO save(ProductDTO productDTO) {
        if (productsRepository.findByName(productDTO.getName()).isPresent()){
            throw new RuntimeException("Product with name '" + productDTO.getName() + "' is already exists");
        };
        Product product = ProductDTO.map(productDTO);
        productsRepository.save(product);
        return ProductDTO.map(product);
    }
    public ProductDTO update(int id, ProductDTO productDTO) {
        Product productToUpdate = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id '" + id + "' is not exist"));
    productToUpdate.setImage(productDTO.getImage());
    productToUpdate.setPrice(productDTO.getPrice());
    productToUpdate.setName(productDTO.getName());
    productToUpdate.setTimestamps(productDTO.getTimestamps());

    productsRepository.save(productToUpdate);
    return ProductDTO.map(productToUpdate);
    }
    public void upload(FileUpload fileUpload) throws IOException {
        List<Product> products = new ArrayList<>();

        try(Reader inputReader = new InputStreamReader(fileUpload.getFile().getInputStream())) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setNumberOfRowsToSkip(1);
            CsvParser parser = new CsvParser(settings);
            List<String[]> rows = parser.parseAll(inputReader);

            for (String[] rec:rows) {
                String name = rec[0];
                String priceString = rec[1];
                int price = Integer.parseInt(priceString);
                String image = rec[2];
                Product product = Product.builder()
                        .name(name)
                        .price(price)
                        .image(image)
                        .build();
                products.add(product);
            }
        }
        productsRepository.saveAll(products);
    }
    public void delete(int id) {
        Product productToDelete = productsRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Product with id '" + id + "' is not found"));

        productsRepository.delete(productToDelete);
    }


}