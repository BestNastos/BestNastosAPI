package com.bestnastos.worker;


import com.bestnastos.constants.PetStatus;

import java.util.*;

public class PetAdapter {

    private Long id;
    private String name;
    private PetStatus status;
    private List<String> photoUrls;
    private Map<String, Object> category;
    private List<Map<String, Object>> tags;
    private Map<String, Object> payload = new HashMap<>();

    public PetAdapter of(String name){
        this.name = name;
        return this;
    }

    public PetAdapter withStatus(PetStatus status) {
        this.status = status;
        return this;
    }

    public PetAdapter withId(Long id) {
        this.id = id;
        return this;
    }

    public PetAdapter withPhotoUrls(String... urls) {
        this.photoUrls = Arrays.asList(urls);
        return this;
    }

    public PetAdapter withCategory(int id, String name) {
        this.category = new HashMap<>();
        category.put("id", id);
        category.put("name", name);
        return this;
    }

    public PetAdapter withTag(int id, String name) {
        if (tags == null) tags = new ArrayList<>();
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", id);
        tag.put("name", name);
        tags.add(tag);
        return this;
    }

//    public Map<String, Object> buildPayload(){
//        return
//                new Pet()
//                        .buildPayload();
//    }
}
