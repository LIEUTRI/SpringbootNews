package com.lieutri.springboot.service.impl;

import com.lieutri.springboot.converter.CategoryConverter;
import com.lieutri.springboot.dto.CategoryDTO;
import com.lieutri.springboot.entity.CategoryEntity;
import com.lieutri.springboot.repository.CategoryRepository;
import com.lieutri.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = categoryConverter.toEntity(categoryDTO);
        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryConverter.toDTO(categoryEntity);
    }
}
