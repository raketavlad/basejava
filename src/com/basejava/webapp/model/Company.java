package com.basejava.webapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;

    private final Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String url) {
        this.homePage = new Link(name, url);
    }

    public void addPeriod(LocalDate fromDate, LocalDate toDate, String title, String content) {
        periods.add(new Period(fromDate, toDate, title, content));
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", periods=" + periods +
                "}";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Company company = (Company) object;

        if (!homePage.equals(company.homePage)) return false;
        return Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        int result = homePage.hashCode();
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }
}
