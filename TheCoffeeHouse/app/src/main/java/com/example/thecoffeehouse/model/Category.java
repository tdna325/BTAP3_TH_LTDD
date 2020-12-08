package com.example.thecoffeehouse.model;

import java.util.List;

public class Category {
    private String name_Poster_355;
    private List<Poster> posterList_355;

    public Category(String name_Poster_355, List<Poster> posterList_355) {
        this.name_Poster_355 = name_Poster_355;
        this.posterList_355 = posterList_355;
    }

    public String getName_Poster_355() {
        return name_Poster_355;
    }

    public void setName_Poster_355(String name_Poster_355) {
        this.name_Poster_355 = name_Poster_355;
    }

    public List<Poster> getPosterList_355() {
        return posterList_355;
    }

    public void setPosterList_355(List<Poster> posterList_355) {
        this.posterList_355 = posterList_355;
    }
}
