package com.lieutri.springboot.converter;

import com.lieutri.springboot.dto.NewsDTO;
import com.lieutri.springboot.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {

    public NewsEntity toEntity(NewsDTO dto){
        NewsEntity entity = new NewsEntity();
        entity.setThumbnail(dto.getThumbnail());
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        return entity;
    }

    public NewsDTO toDTO(NewsEntity entity){
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setThumbnail(entity.getThumbnail());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setShortDescription(entity.getShortDescription());
        dto.setCategoryCode(entity.getCategory().getCode());
        dto.setCategoryName(entity.getCategory().getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }
}
