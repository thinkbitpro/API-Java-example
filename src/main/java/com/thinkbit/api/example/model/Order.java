package com.thinkbit.api.example.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;

public class Order {
    private final HashMap<String, Object> any = new HashMap<>();
    @JsonProperty("id")
    private String id;
    private String pair;
    private String type;
    private String side;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal unclosed;
    @JsonProperty("create_time")
    private long createTime;
    @JsonProperty("remove_time")
    private long removeTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUnclosed() {
        return unclosed;
    }

    public void setUnclosed(BigDecimal unclosed) {
        this.unclosed = unclosed;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(long removeTime) {
        this.removeTime = removeTime;
    }

    @JsonAnySetter
    public void setAny(String key, Object value) {
        this.any.put(key, value);
    }

    @JsonAnyGetter
    public HashMap<String, Object> getAny() {
        return any;
    }
}
