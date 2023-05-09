package com.grocer.controllers;

import com.grocer.models.dto.CategoryDTO;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.Category;
import com.grocer.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;


    @GetMapping
    public List<CategoryDTO> getCategories() {
        return categoryService.findAll()
                .stream()
                .map(CategoryDTO::map)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        return CategoryDTO.map(category);
    }
    @PostMapping
    public void save(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
    }
    @PostMapping("/upload")
    public void upload(FileUpload categoriesUpload) throws IOException {
        categoryService.upload(categoriesUpload);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") int id,@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(id, categoryDTO);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        categoryService.delete(id);
    }
}