package com.bestnastos;

import com.bestnastos.constants.PetStatus;

import java.util.*;

public class PetBuilder {

    private Long id;
    private String name;
    private PetStatus status;
    private List<String> photoUrls;
    private Map<String, Object> category;
    private List<Map<String, Object>> tags;
    private Map<String, Object> payload = new HashMap<>();

    public PetBuilder withStatus(PetStatus status) {
        this.status = status;
        return this;
    }

    public PetBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public PetBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PetBuilder withPhotoUrls(String... urls) {
        this.photoUrls = Arrays.asList(urls);
        return this;
    }

    public PetBuilder withCategory(int id, String name) {
        this.category = new HashMap<>();
        category.put("id", id);
        category.put("name", name);
        return this;
    }

    public PetBuilder withTag(int id, String name) {
        if (tags == null) tags = new ArrayList<>();
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", id);
        tag.put("name", name);
        tags.add(tag);
        return this;
    }

    public Map<String, Object> buildPayload(){
        payload.put("status", status);
        payload.put("id", id);
        payload.put("name", name);
        payload.put("photoUrls", photoUrls);
        payload.put("category", category);
        payload.put("tags", tags);

        return payload;
    }
}
