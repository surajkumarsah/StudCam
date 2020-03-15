package com.example.studcam.model;

public class ScreenItem
{
    int ScreenTag;
    String Title, Description;


    public int getScreenTag() {
        return ScreenTag;
    }

    public void setScreenTag(int screenTag) {
        ScreenTag = screenTag;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


    public ScreenItem(int ScreenTag, String Title, String Description)
    {
        this.ScreenTag = ScreenTag;
        this.Description = Description;
        this.Title = Title;
    }

}
