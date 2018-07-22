package com.thinkbit.api.example.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class OrderCancel {
    private Map<String, Object> any = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAny() {
        return any;
    }

    @JsonAnySetter
    public void setAny(String key, Object value) {
        this.any.put(key, value);
    }

    @Override
    public String toString() {
        return "OrderCancel{" +
                "any=" + any +
                '}';
    }
}
