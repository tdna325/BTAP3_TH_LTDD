package com.example.thecoffeehouse.model;

import java.util.List;

public class ProductCategory {
    private String title_355;
    private List<Product> list_355;

    public ProductCategory(String title_355, List<Product> list_355) {
        this.title_355 = title_355;
        this.list_355 = list_355;
    }

    public String getTitle_355() {
        return title_355;
    }

    public void setTitle_355(String title_355) {
        this.title_355 = title_355;
    }

    public List<Product> getList_355() {
        return list_355;
    }

    public void setList_355(List<Product> list_355) {
        this.list_355 = list_355;
    }
}
