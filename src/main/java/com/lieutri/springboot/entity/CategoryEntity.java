package com.lieutri.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity extends GeneralFields {

    private String code;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<NewsEntity> newses = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NewsEntity> getNewses() {
        return newses;
    }

    public void setNewses(List<NewsEntity> newses) {
        this.newses = newses;
    }
}
