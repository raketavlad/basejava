package com.basejava.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {

    private final String uuid;
    private final String fullName;

    private final Map<ContactType, String> contacts = new LinkedHashMap<>();
    private final Map<SectionType, AbstractSection> sections = new LinkedHashMap<>();

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(uuid, "uuid must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void addContact(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public void updateContact(ContactType contactType, String newValue) {
        contacts.replace(contactType, newValue);
    }

    public void printContacts() {
        System.out.println("КОНТАКТЫ:");
        for (ContactType contact : contacts.keySet()) {
            System.out.println(contact.getTitle() + ": " + contacts.get(contact));
        }
        System.out.println();
    }

    public void addSection(SectionType sectionType, AbstractSection section) {
        sections.put(sectionType, section);
    }

    public void printSections() {
        for (SectionType sectionType : sections.keySet()) {
            System.out.println(sectionType.getTitle().toUpperCase());
            sections.get(sectionType).printContent();
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName.equals(resume.fullName);
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + fullName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }
}
