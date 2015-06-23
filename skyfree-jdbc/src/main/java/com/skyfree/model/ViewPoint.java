package com.skyfree.model;

/**
 * Copyright @ 2015 OPS
 * Author: tingfang.bao <mantingfangabc@163.com>
 * DateTime: 15/6/23 18:23
 */
public class ViewPoint {
    private int id;
    private String text;
    private byte[] image;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
