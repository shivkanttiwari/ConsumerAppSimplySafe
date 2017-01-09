package com.example.tiuadmin.simplysafeconusmerapp.Models;

/**
 * Created by tiuadmin on 09/01/17.
 */

public class MainMenuDashboard {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String id;
    String name;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    int image;
    public MainMenuDashboard(String id, String name,int image) {
        this.id = id;
        this.name = name;
        this.image=image;
    }


}
