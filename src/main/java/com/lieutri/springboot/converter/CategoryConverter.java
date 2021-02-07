package com.lieutri.springboot.converter;

import com.lieutri.springboot.dto.CategoryDTO;
import com.lieutri.springboot.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity entity = new CategoryEntity();
        entity.setCode(categoryDTO.getCode());
        entity.setName(categoryDTO.getName());
        return entity;
    }

    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setCode(categoryEntity.getCode());
        dto.setName(categoryEntity.getName());
        return dto;
    }
}
