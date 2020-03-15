package com.example.studcam.model;

public class QuestionsViewModel {

    private String Image;
    private String submittedby;
    private String date;
    private String userId;
    private String userEmail;
    private String desc;
    private String prlmid;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrlmid() {
        return prlmid;
    }

    public void setPrlmid(String prlmid) {
        this.prlmid = prlmid;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getSubmittedby() {
        return submittedby;
    }

    public void setSubmittedby(String submittedby) {
        this.submittedby = submittedby;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public QuestionsViewModel()
    {

    }


    public QuestionsViewModel(String image, String submittedby, String date, String prlmid,String userId, String userEmail, String desc) {
        Image = image;
        this.submittedby = submittedby;
        this.date = date;
        this.prlmid = prlmid;
        this.desc = desc;
        this.userEmail = userEmail;
        this.userId = userId;
    }




}
