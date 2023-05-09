package com.grocer.models.dto;

import com.grocer.models.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String image;
    private LocalDateTime timestamps;

    public static ProductDTO map(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .image(product.getImage())
                .price(product.getPrice())
                .name(product.getName())
                .timestamps(product.getTimestamps())
                .build();
    }

    public static Product map(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .image(productDTO.getImage())
                .price(productDTO.getPrice())
                .name(productDTO.getName())
                .timestamps(productDTO.getTimestamps())
                .build();
    }

    public static List<ProductDTO> mapToDto(List<Product> products) {
        return products.stream().map(ProductDTO::map).collect(Collectors.toList());
    }
    public static List<Product> mapFromDto(List<ProductDTO> products) {
        return products.stream().map(ProductDTO::map).collect(Collectors.toList());
    }

}