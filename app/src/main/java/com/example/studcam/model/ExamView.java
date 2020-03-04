package com.example.studcam.model;

public class ExamView {

    private String imageLink;
    private String text;
    private String id;

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

    private String name;

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public ExamView() {
    }



    public ExamView(String imageLink, String text, String name, String id) {
        this.imageLink = imageLink;
        this.text = text;
        this.id = id;
        this.name = name;

    }

}
