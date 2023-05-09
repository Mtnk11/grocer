package com.grocer.models.dto;

import com.grocer.models.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    private String name;
    private Category parent;
    private Set<Category> children = new HashSet<>();
    private LocalDateTime timestamps;

    public static Category map(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .children(categoryDTO.getChildren())
                .parent(categoryDTO.getParent())
                .timestamps(categoryDTO.getTimestamps())
                .build();
    }
    public static CategoryDTO map(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .children(category.getChildren())
                .parent(category.getParent())
                .timestamps(category.getTimestamps())
                .build();
    }
}