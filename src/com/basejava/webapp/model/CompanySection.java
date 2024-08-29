package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class CompanySection extends AbstractSection {

    private List<Company> companies = new ArrayList<>();

    public void addCompany(String companyName, String website) {
        companies.add(new Company(companyName, website));
    }

    public void addPeriod(String companyName, String fromDate, String toDate, String title, String content) {
        for (Company company : companies) {
            if (company.getName().equals(companyName)) {
                company.addPeriod(fromDate, toDate, title, content);
            }
        }
    }

//    public List<Company> getContent() {
//        return companies;
//    }

    @Override
    public void printContent() {
        for (Company company : companies) {
            System.out.println("                " + company.getName() + " (" + company.getWebsite() + ")");
            for (Period period : company.getPeriods()) {
                System.out.println(period.getDate() + " " + period.getTitle());
                System.out.println("                " + period.getContent());
            }
            System.out.println();
        }
    }
}
