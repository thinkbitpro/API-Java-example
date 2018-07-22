package com.thinkbit.api.example.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.HashMap;
import java.util.Map;

public class OrderCreate {

    private String orderId;
    private Map<String, Object> any = new HashMap<>();

    @JsonGetter("order_id")
    public String getOrderId() {
        return orderId;
    }

    @JsonSetter("order_id")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
        return "OrderCreate{" +
                "orderId='" + orderId + '\'' +
                ", any=" + any +
                '}';
    }
}
