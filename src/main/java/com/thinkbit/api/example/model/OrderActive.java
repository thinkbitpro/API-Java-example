package com.thinkbit.api.example.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderActive extends ArrayList<Order> {
    private final HashMap<String, Object> any = new HashMap<>();

    @JsonAnySetter
    public void setAny(String key, Object value) {
        this.any.put(key, value);
    }

    @JsonAnyGetter
    public HashMap<String, Object> getAny() {
        return any;
    }
}
