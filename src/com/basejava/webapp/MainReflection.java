package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new uuid");

        // TODO через отражение (reflection) нужно вызвать resume.toString
        System.out.println(resume);
    }
}
