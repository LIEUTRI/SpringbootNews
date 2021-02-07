package com.lieutri.springboot.service;

import com.lieutri.springboot.dto.NewsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewsService {
    NewsDTO save(NewsDTO newsDTO);
    void delete(long[] IDs);
    List<NewsDTO> findAll(Pageable pageable);
    List<NewsDTO> findAll();
    NewsDTO update(long id, String jsonString);
    int totalItem();
    NewsDTO findOne(long id);
}
