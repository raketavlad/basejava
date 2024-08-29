package com.basejava.webapp.model;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1", "First Resume");

        resume1.addContact(ContactType.EMAIL, "zeros@mail.ru");
        resume1.addContact(ContactType.PHONE_NUMBER, "88005553535");
        resume1.addContact(ContactType.SKYPE, "zZeRoSs");
        resume1.addContact(ContactType.GITHUB, "raketavlad");
        resume1.updateContact(ContactType.EMAIL, "chertolet@mail.ru");

        TextSection objective = new TextSection();
        objective.addContent("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume1.addSection(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection();
        personal.addContent("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                "Пурист кода и архитектуры");
        resume1.addSection(SectionType.PERSONAL, personal);

        ListSection achievement = new ListSection();
        achievement.addContent("Организация команды и успешная реализация Java проектов для сторонних заказчиков:" +
                " приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов" +
                " на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для" +
                " комплексных DIY смет");
        achievement.addContent("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java" +
                " Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
                " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500" +
                " выпускников.");
        resume1.addSection(SectionType.ACHIEVEMENT, achievement);

        ListSection qualifications = new ListSection();
        qualifications.addContent("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.addContent("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume1.addSection(SectionType.QUALIFICATIONS, qualifications);




        // Рабочая схема для компаний
        CompanySection experience = new CompanySection();
        experience.addCompany("Google", "www.google.com");
        experience.addCompany("yandex", "www.yandex.ru");
        experience.addPeriod("Google", "01/2024", "03/2024", "junior QA", "Тестировал продукт");
        experience.addPeriod("Google", "04/2024", "08/2024", "middle QA", "Лучше тестировал продукт");

        resume1.addSection(SectionType.EXPERIENCE, experience);

        // Вывод резюме в консоль
        resume1.printContacts();
        resume1.printSections();




    }
}
