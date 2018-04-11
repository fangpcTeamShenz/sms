package com.pj.web.model;

public class RouteForce {
    private Long rotueId;

    private String routeName;

    private Long productId;

    private Long channelId;

    private Integer priority;

    private Integer useable;

    private Integer rate;

    private Long userId;

    public Long getRotueId() {
        return rotueId;
    }

    public void setRotueId(Long rotueId) {
        this.rotueId = rotueId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName == null ? null : routeName.trim();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getUseable() {
        return useable;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}