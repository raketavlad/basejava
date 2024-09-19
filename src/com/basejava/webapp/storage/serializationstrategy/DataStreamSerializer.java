package com.basejava.webapp.storage.serializationstrategy;

import com.basejava.webapp.model.*;
import com.basejava.webapp.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            /* Запись контактов. Создается мапа контактов. Для определения количества элементов к записи (и дальнейшему
             чтению), записывается размер мапы.  Переберая мапу записывается ключ и значение. */
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            /* Запись секций */
            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                AbstractSection section = entry.getValue();
                if (section instanceof TextSection) {
                    dos.writeUTF(section.toString());
                } else if (section instanceof ListSection) {
                    ListSection listSection = (ListSection) section;
                    dos.writeInt(listSection.getItems().size());
                    for (String item : listSection.getItems()) {
                        dos.writeUTF(item);
                    }
                } else if (section instanceof CompanySection) {
                    CompanySection companySection = (CompanySection) section;
                    dos.writeInt(companySection.getCompanies().size());
                    for (Company company : companySection.getCompanies()) {
                        dos.writeUTF(company.getLink().getName());
                        dos.writeUTF(company.getLink().getUrl());
                        dos.writeInt(company.getPeriods().size());
                        for (Period period : company.getPeriods()) {
                            dos.writeUTF(period.getStartDate().toString());
                            dos.writeUTF(period.getEndDate().toString());
                            dos.writeUTF(period.getTitle());
                            dos.writeUTF(period.getDescription());
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
       try (DataInputStream dis = new DataInputStream(is)) {
           Resume resume = new Resume(dis.readUTF(), dis.readUTF());

           // Считывание контактов и добавление их в резюме
           int countContacts = dis.readInt();
           for (int i = 0; i < countContacts; i++) {
               resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
           }

           // Считывание секций и добавление их в резюме
           int countSections = dis.readInt();
           for (int i = 0; i < countSections; i++) {
               SectionType sectionType = SectionType.valueOf(dis.readUTF());
               if (sectionType == SectionType.PERSONAL || sectionType == SectionType.OBJECTIVE) {
                   resume.addSection(sectionType, new TextSection(dis.readUTF()));
               } else if (sectionType == SectionType.QUALIFICATIONS || sectionType == SectionType.ACHIEVEMENT) {
                   int countItems = dis.readInt();
                   List<String> items = new ArrayList<>();
                   for (int j = 0; j < countItems; j++) {
                       items.add(dis.readUTF());
                   }
                   resume.addSection(sectionType, new ListSection(items));
               } else {
                   int countCompanies = dis.readInt();
                   List<Company> companies = new ArrayList<>();
                   for (int j = 0; j < countCompanies; j++) {
                       Company company = new Company(dis.readUTF(), dis.readUTF());
                       int countPeriods = dis.readInt();
                       for (int k = 0; k < countPeriods; k++) {
                           company.addPeriod(getLocalDate(dis.readUTF()), getLocalDate(dis.readUTF()),
                                   dis.readUTF(), dis.readUTF());
                       }
                       companies.add(company);
                   }
                   resume.addSection(sectionType, new CompanySection(companies));
               }
           }
           return resume;
       }
    }

    private LocalDate getLocalDate(String date) {
        String[] dateArray = date.split("-");
        return  DateUtil.of(Integer.parseInt(dateArray[0]), Month.of(Integer.parseInt(dateArray[1])));
    }
}
