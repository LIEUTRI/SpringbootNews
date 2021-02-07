package com.lieutri.springboot.repository;

import com.lieutri.springboot.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
}
