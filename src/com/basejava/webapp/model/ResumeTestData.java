package com.basejava.webapp.model;

import com.basejava.webapp.util.DateUtil;

import java.time.Month;
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
        company1.addPeriod(DateUtil.of(2024, Month.JANUARY), DateUtil.of(2024, Month.FEBRUARY), "java student", "Student in google");
        company1.addPeriod(DateUtil.of(2024, Month.MARCH), DateUtil.of(2024, Month.APRIL), "java junior-", "Jun dev in google");
        Company company2 = new Company("VK", "www.vk.com");
        company2.addPeriod(DateUtil.of(2024, Month.MAY), DateUtil.of(2024, Month.JUNE), "java junior", "Jun dev in vk");
        List<Company> experienceCompanies = new ArrayList<>();
        experienceCompanies.add(company1);
        experienceCompanies.add(company2);
        CompanySection experience = new CompanySection(experienceCompanies);
        resume1.addSection(SectionType.EXPERIENCE, experience);
        System.out.println(resume1.getSection(SectionType.EXPERIENCE).toString());

        // Добавляю образование
        Company company3 = new Company("yandex.practicum", "www.yandexpracticum.ru");
        company3.addPeriod(DateUtil.of(2023, Month.JANUARY), DateUtil.of(2023, Month.JUNE), "qa student", "Student in YP");
        Company company4 = new Company("javaops", "www.javaops.ru");
        company4.addPeriod(DateUtil.of(2023, Month.JULY), DateUtil.of(2023, Month.DECEMBER), "java student", "Student in javaops");
        List<Company> educationCompanies = new ArrayList<>();
        educationCompanies.add(company3);
        educationCompanies.add(company4);
        CompanySection education = new CompanySection(educationCompanies);
        resume1.addSection(SectionType.EDUCATION, education);
        System.out.println(resume1.getSection(SectionType.EDUCATION).toString());

        Resume resumeTest = createResume("uuid1", "Rybalko V.A.");
    }

    public static Resume createResume(String uuid, String fullName) {

        // Заполнение контактов
        Resume resume = new Resume(uuid, fullName);
        for (ContactType contactType : ContactType.values()) {
            resume.addContact(contactType, "Test");
        }

        // Заполнение секции Личные качества
        resume.addSection(SectionType.PERSONAL, new TextSection(""));

        // Заполнение секции Позиция
        resume.addSection(SectionType.OBJECTIVE, new TextSection(""));

        //Заполнение секции Достижения
        List<String> achievement = new ArrayList<>();
        achievement.add("Первое достижение");
        achievement.add("Второе достижение");
        achievement.add("Третье достижение");
        resume.addSection(SectionType.ACHIEVEMENT, new ListSection(achievement));

        // Заполнение секции Квалификация
        List<String> qualifications = new ArrayList<>();
        qualifications.add("Первая квалификация");
        qualifications.add("Вторая квалификация");
        qualifications.add("Третья квалификация");
        resume.addSection(SectionType.QUALIFICATIONS, new ListSection(qualifications));

        // Заполнение секции Опыт работы
        Company company1 = new Company("Google", "www.google.com");
        company1.addPeriod(DateUtil.of(2024, Month.JANUARY), DateUtil.of(2024, Month.FEBRUARY), "java student", "Student in google");
        company1.addPeriod(DateUtil.of(2024, Month.MARCH), DateUtil.of(2024, Month.APRIL), "java junior-", "Jun dev in google");
        Company company2 = new Company("VK", "www.vk.com");
        company2.addPeriod(DateUtil.of(2024, Month.MAY), DateUtil.of(2024, Month.JUNE), "java junior", "Jun dev in vk");
        List<Company> experienceCompanies = new ArrayList<>();
        experienceCompanies.add(company1);
        experienceCompanies.add(company2);
        CompanySection experience = new CompanySection(experienceCompanies);
        resume.addSection(SectionType.EXPERIENCE, experience);

        // Заполнение секции Образование
        Company company3 = new Company("yandex.practicum", "www.yandexpracticum.ru");
        company3.addPeriod(DateUtil.of(2023, Month.JANUARY), DateUtil.of(2023, Month.JUNE), "qa student", "Student in YP");
        Company company4 = new Company("javaops", "www.javaops.ru");
        company4.addPeriod(DateUtil.of(2023, Month.JULY), DateUtil.of(2023, Month.DECEMBER), "java student", "Student in javaops");
        List<Company> educationCompanies = new ArrayList<>();
        educationCompanies.add(company3);
        educationCompanies.add(company4);
        CompanySection education = new CompanySection(educationCompanies);
        resume.addSection(SectionType.EDUCATION, education);

        return resume;
    }
}
