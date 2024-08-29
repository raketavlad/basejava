package com.basejava.webapp.model;

public class TextSection extends AbstractSection {

    private String content;

    public void addContent(String text) {
        content = text;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void printContent() {
        System.out.println(content);
    }
}
