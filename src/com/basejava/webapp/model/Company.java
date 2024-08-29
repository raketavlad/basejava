package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private String website;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void addPeriod(String fromDate, String toDate, String title, String content) {
        periods.add(new Period(fromDate, toDate, title, content));
    }
}
