package com.basejava.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        // Создаю резюме
        Resume resume1 = new Resume("uuid1", "First Resume");
        // Добавляю email
        resume1.addContact(ContactType.EMAIL, "pochta@mail.ru");
        System.out.println(resume1.getContact(ContactType.EMAIL).toString());


        // Добавляю личные качества
        resume1.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика," +
                " креативность, инициативность. Пурист кода и архитектуры"));

        // Добавляю позицию
        resume1.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного" +
                " обучения по Java Web и Enterprise технологиям"));

        // Добавляю достижения
        List<String> achievement = new ArrayList<>();
        achievement.add("Первое достижение");
        achievement.add("Второе достижение");
        achievement.add("Третье достижение");
        resume1.addSection(SectionType.ACHIEVEMENT, new ListSection(achievement));
        System.out.println(resume1.getSection(SectionType.ACHIEVEMENT).toString());

        // Добавляю квалификацию
        List<String> qualifications = new ArrayList<>();
        qualifications.add("Первая квалификация");
        qualifications.add("Вторая квалификация");
        qualifications.add("Третья квалификация");
        resume1.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));
        System.out.println(resume1.getSection(SectionType.QUALIFICATIONS).toString());

        // Добавляю опыт работы
        Company company1 = new Company("Google", "www.google.com");
        company1.addPeriod("01.2024", "02.2024", "java student", "Student in google");
        company1.addPeriod("03.2024", "04.2024", "java junior-", "Jun dev in google");
        Company company2 = new Company("VK", "www.vk.com");
        company2.addPeriod("05.2024", "06.2024", "java junior", "Jun dev in vk");
        List<Company> experienceCompanies = new ArrayList<>();
        experienceCompanies.add(company1);
        experienceCompanies.add(company2);
        CompanySection experience = new CompanySection(experienceCompanies);
        resume1.addSection(SectionType.EXPERIENCE, experience);
        System.out.println(resume1.getSection(SectionType.EXPERIENCE).toString());

        // Добавляю образование
        Company company3 = new Company("yandex.practicum", "www.yandexpracticum.ru");
        company3.addPeriod("01.2023", "06.2023", "qa student", "Student in YP");
        Company company4 = new Company("javaops", "www.javaops.ru");
        company4.addPeriod("07.2023", "12.2023", "java student", "Student in javaops");
        List<Company> educationCompanies = new ArrayList<>();
        educationCompanies.add(company3);
        educationCompanies.add(company4);
        CompanySection education = new CompanySection(educationCompanies);
        resume1.addSection(SectionType.EDUCATION, education);
        System.out.println(resume1.getSection(SectionType.EDUCATION).toString());
    }
}
