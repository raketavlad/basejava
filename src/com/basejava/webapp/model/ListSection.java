package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends AbstractSection {

    private List<String> content = new ArrayList<>();

    public void addContent(String text) {
        content.add(text);
    }

    public List<String> getContent() {
        return content;
    }

    @Override
    public void printContent() {
        for (String text : content) {
            System.out.println("- " + text);
        }
    }
}
