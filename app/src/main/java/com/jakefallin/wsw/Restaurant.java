package com.jakefallin.wsw;

/**
 * Created by JakeFallin on 9/30/2016.
 */

public class Restaurant {
    private String title;

    public Restaurant(String t)
    {
        title = t;

    }
    public Restaurant() {
        title = "test";

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
