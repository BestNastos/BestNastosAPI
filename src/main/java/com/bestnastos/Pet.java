package com.bestnastos;

import java.util.*;

public class Pet {

    private Long id;
    private String name;
    private com.bestnastos.constants.PetStatus status;
    private List<String> photoUrls;
    private Map<String, Object> category;
    private List<Map<String, Object>> tags;
    private Map<String, Object> payload = new HashMap<>();

    public Pet withStatus(com.bestnastos.constants.PetStatus status) {
        this.status = status;
        payload.put("status", status);
        return this;
    }

    public Pet withId(Long id) {
        this.id = id;
        payload.put("id", id);
        return this;
    }

    public Pet withName(String name) {
        this.name = name;
        payload.put("name", name);
        return this;
    }

    public Pet withPhotoUrls(String... urls) {
        this.photoUrls = Arrays.asList(urls);
        payload.put("photoUrls", photoUrls);
        return this;
    }

    public Pet withCategory(int id, String name) {
        this.category = new HashMap<>();
        category.put("id", id);
        category.put("name", name);
        payload.put("category", category);
        return this;
    }

    public Pet withTag(int id, String name) {
        if (tags == null) tags = new ArrayList<>();
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", id);
        tag.put("name", name);
        tags.add(tag);
        payload.put("tags", tags);
        return this;
    }

    public Map<String, Object> getPayload(){
        return payload;
    }
}
