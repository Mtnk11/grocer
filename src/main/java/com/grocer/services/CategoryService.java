package com.grocer.services;

import com.grocer.models.dto.CategoryDTO;
import com.grocer.models.dto.FileUpload;
import com.grocer.models.Category;
import com.grocer.repositories.CategoryRepository;
import com.grocer.utils.ParserUtils;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    public Category findById(int id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id '" + id + "' not found"));
    }

    public Integer save(CategoryDTO categoryDTO) {
        if (categoryRepository.findByName(categoryDTO.getName()).isPresent()) {
            throw new RuntimeException("Category with name '" + categoryDTO.getName() + "' already exist");
        }
        Category categoryToSave = CategoryDTO.map(categoryDTO);
        return categoryRepository.save(categoryToSave).getId();
    }

    public CategoryDTO update(int id, CategoryDTO categoryDTO) {
        Category categoryToUpdate = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
        categoryToUpdate.setName(categoryDTO.getName());
        categoryToUpdate.setTimestamps(categoryDTO.getTimestamps());
        categoryRepository.save(categoryToUpdate);
        return CategoryDTO.map(categoryToUpdate);
    }


    public void upload(FileUpload categoriesUpload) throws IOException {
        List<Category> categories = new ArrayList<>();

        try (Reader inputReader = new InputStreamReader(categoriesUpload.getFile().getInputStream())) {
            CsvParserSettings settings = new CsvParserSettings();
            settings.setNumberOfRowsToSkip(1);
            CsvParser parser = new CsvParser(settings);
            List<String[]> parsedRows = parser.parseAll(inputReader);

            for (String[] rec : parsedRows) {
                String name = rec[0];

                String parentIdStr = rec[2];
                int i = Integer.parseInt(parentIdStr);
                Category parent = null;
                if (i > -1) {
                    parent = new Category();
                    parent.setId(i);
                }
                Category category = Category.builder()
                        .id(Integer.parseInt(rec[1]))
                        .name(name)
                        .parent(parent)
                        .build();
                categories.add(category);
                categoryRepository.save(category);
            }
        }
//        categoryRepository.saveAll(categories);
    }

    public void delete(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id " + id + " not found"));
        categoryRepository.delete(category);
    }
}