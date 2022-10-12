package com.manzi.comradesofficials;

import android.media.Image;

public class ViongoziModel {
    String Name;
    String Announcement;
    String Image;

    public ViongoziModel() {

    }

    public ViongoziModel(String name,String announcement, String image){

        Name=name;
        Announcement=announcement;
        this.Image= image;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAnnouncement() {
        return Announcement;
    }

    public void setAnnouncement(String announcement) {
        Announcement = announcement;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
