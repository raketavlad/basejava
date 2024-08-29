package com.basejava.webapp.model;

public class Period {

    private final String fromDate;
    private final String toDate;
    private final String title;
    private final String content;

    public Period(String fromDate, String toDate, String title, String content) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return fromDate + "-" + toDate;
    }
}
