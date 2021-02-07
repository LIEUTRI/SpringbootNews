package com.lieutri.springboot.service.impl;

import com.lieutri.springboot.converter.NewsConverter;
import com.lieutri.springboot.dto.NewsDTO;
import com.lieutri.springboot.entity.CategoryEntity;
import com.lieutri.springboot.entity.NewsEntity;
import com.lieutri.springboot.repository.CategoryRepository;
import com.lieutri.springboot.repository.NewsRepository;
import com.lieutri.springboot.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class NewsService implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewsConverter newsConverter;

    @Override
    public NewsDTO save(NewsDTO newsDTO){
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newsDTO.getCategoryCode());
        NewsEntity newsEntity = newsConverter.toEntity(newsDTO);
        newsEntity.setCategory(categoryEntity);
        newsEntity = newsRepository.save(newsEntity);
        return newsConverter.toDTO(newsEntity);
    }

    @Override
    public void delete(long[] IDs) {
        for (long id: IDs){
            newsRepository.deleteById(id);
        }
    }

    public List<NewsDTO> findAll(Pageable pageable) {
        List<NewsDTO> newsList = new ArrayList<>();
        List<NewsEntity> newsEntities = newsRepository.findAll(pageable).getContent();
        for (NewsEntity entity: newsEntities){
            newsList.add(newsConverter.toDTO(entity));
        }
        return newsList;
    }

    public List<NewsDTO> findAll() {
        List<NewsDTO> newsList = new ArrayList<>();
        List<NewsEntity> newsEntities = newsRepository.findAll();
        for (NewsEntity entity: newsEntities){
            newsList.add(newsConverter.toDTO(entity));
        }
        return newsList;
    }

    @Override
    public int totalItem() {
        return (int) newsRepository.count();
    }

    @Override
    public NewsDTO update(long id, String jsonString) {

        Optional<NewsEntity> optionalNewsEntity;
        try {
            JSONObject json = new JSONObject(jsonString);

            optionalNewsEntity = newsRepository.findById(id);

            if (optionalNewsEntity.isPresent()){
                NewsEntity entity = optionalNewsEntity.get();

                Iterator iterator = json.keys();
                while (iterator.hasNext()){
                    String key = iterator.next().toString();
                    switch (key){
                        case "thumbnail":
                            entity.setThumbnail(json.getString(key));
                            break;
                        case "title":
                            entity.setTitle(json.getString(key));
                            break;
                        case "shortDescription":
                            entity.setShortDescription(json.getString(key));
                            break;
                        case "content":
                            entity.setContent(json.getString(key));
                            break;
                        default:
                            System.out.println("key[" + key +"] is incorrect (issue from client)");
                            return null;
                    }
                }

                newsRepository.save(entity);

                return newsConverter.toDTO(entity);
            } else {
                System.out.println("No item found!");
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NewsDTO findOne(@PathVariable(name = "id") long id){
        Optional<NewsEntity> optional = newsRepository.findById(id);
        NewsEntity newsEntity;
        NewsDTO newsDTO = null;
        if (optional.isPresent()){
            newsEntity = optional.get();
            newsDTO = newsConverter.toDTO(newsEntity);
        }
        return newsDTO;
    }
}
