package com.example.parsing_json_url_recyclerview;

public class MovieModelClass {
    private String id;
    private String name;
    private String image_link;

    public MovieModelClass(String id, String name, String image_link) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
    }

    public MovieModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }
}
