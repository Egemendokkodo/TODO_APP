package com.uygulamalarim.todo_app.Domain;

public class taskDomain {
    private String dateTime;
    private String title;
    private String description;
    private String deadLine;


    public taskDomain(String dateTime, String title, String description, String deadLine) {
        this.dateTime = dateTime;
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;

    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

}
