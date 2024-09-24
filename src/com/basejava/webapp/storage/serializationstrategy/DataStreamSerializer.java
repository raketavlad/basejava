package com.basejava.webapp.storage.serializationstrategy;

import com.basejava.webapp.model.*;
import com.basejava.webapp.util.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer<E> implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            /* Запись контактов. Создается мапа контактов. Для определения количества элементов к записи (и дальнейшему
             чтению), записывается размер мапы.  Переберая мапу записывается ключ и значение. */
            Map<ContactType, String> contacts = resume.getContacts();

            writeWithException(contacts.entrySet(), dos, element -> {
                dos.writeUTF(element.getKey().name());
                dos.writeUTF(element.getValue());
            });

            /* Запись секций */
            Map<SectionType, AbstractSection> sections = resume.getSections();
            writeWithException(sections.entrySet(), dos, section -> {
                dos.writeUTF(section.getKey().name());
                SectionType st = section.getKey();
                switch (st) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(section.getValue().toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection ls = (ListSection) section.getValue();
                        writeWithException(ls.getItems(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        CompanySection cs = (CompanySection) section.getValue();
                        writeWithException(cs.getCompanies(), dos, company -> {
                            dos.writeUTF(company.getLink().getName());
                            dos.writeUTF(company.getLink().getUrl());
                            writeWithException(company.getPeriods(), dos, period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            // Считывание контактов и добавление их в резюме
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));


            // Считывание секций и добавление их в резюме
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.addSection(sectionType, new TextSection((dis.readUTF())));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = new ArrayList<>();
                        readWithException(dis, () -> items.add(dis.readUTF()));
                        resume.addSection(sectionType, new ListSection(items));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Company> companies = new ArrayList<>();
                        readWithException(dis, () -> {
                            Company company = new Company(dis.readUTF(), dis.readUTF());
                            readWithException(dis, () -> {
                                company.addPeriod(getLocalDate(dis.readUTF()), getLocalDate(dis.readUTF()),
                                        dis.readUTF(), dis.readUTF());
                            });
                            companies.add(company);
                        });
                        resume.addSection(sectionType, new CompanySection(companies));
                        break;
                }
            });
            return resume;
        }
    }

    private LocalDate getLocalDate(String date) {
        String[] dateArray = date.split("-");
        return DateUtil.of(Integer.parseInt(dateArray[0]), Month.of(Integer.parseInt(dateArray[1])));
    }

    private <W> void writeWithException(Collection<W> collection, DataOutputStream dos,
                                        DataWriting<W> dw) throws IOException {
        dos.writeInt(collection.size());
        for (W element : collection) {
            dw.writeElements(element);
        }
    }

    private void readWithException(DataInputStream dis,
                                   DataReading dr) throws IOException {
        int countElements = dis.readInt();
        for (int i = 0; i < countElements; i++) {
            dr.readElements();
        }
    }

    @FunctionalInterface
    public interface DataWriting<T> {
        void writeElements(T t) throws IOException;
    }

    @FunctionalInterface
    public interface DataReading {
        void readElements() throws IOException;
    }
}

