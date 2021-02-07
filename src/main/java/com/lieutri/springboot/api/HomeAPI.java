package com.lieutri.springboot.api;

import com.lieutri.springboot.api.paging.Paging;
import com.lieutri.springboot.dto.CategoryDTO;
import com.lieutri.springboot.dto.NewsDTO;
import com.lieutri.springboot.service.ICategoryService;
import com.lieutri.springboot.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController()
@CrossOrigin(origins = "http://localhost:3000")
public class HomeAPI {

    @Autowired
    private INewsService newsService;

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/news")
    public NewsDTO addNews(@RequestBody NewsDTO newsDTO){
        return newsService.save(newsDTO);
    }

    @GetMapping("/news")
    public Paging<NewsDTO> pagingNews(@RequestParam(value = "page", required = false) Integer page,
                                      @RequestParam(value = "limit", required = false) Integer limit){

        Paging<NewsDTO> newsOutput = new Paging<>();

        if (page != null && limit != null){

            if (page < 1) return new Paging<>();
            page -= 1;

            newsOutput.setPage(page);
            newsOutput.setListResult(newsService.findAll(PageRequest.of(page, limit, Sort.by("id").descending())));
            newsOutput.setTotalPage((int) Math.ceil((double) (newsService.totalItem()) / limit));
        } else {
            newsOutput.setListResult(newsService.findAll());
        }
        return newsOutput;
    }

    @PutMapping(value = "/news/{id}")
    public NewsDTO update(@RequestBody String jsonString, @PathVariable("id") long id){
        return newsService.update(id, jsonString);
    }

    @GetMapping("/news/{id}")
    public NewsDTO getNews(@PathVariable("id") long id){
        return newsService.findOne(id);
    }

    @DeleteMapping("/news")
    public void deleteNews(@RequestBody long[] IDs){
        newsService.delete(IDs);
    }

    @PostMapping(value = "/category")
    public CategoryDTO addCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }
}
