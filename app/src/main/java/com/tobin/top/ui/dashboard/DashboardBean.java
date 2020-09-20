package com.tobin.top.ui.dashboard;

import java.io.Serializable;

public class DashboardBean implements Serializable {

    private String name;
    private int imageId;

    public DashboardBean(String name, int imageId){
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
