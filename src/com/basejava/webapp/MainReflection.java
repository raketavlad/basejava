package com.basejava.webapp;

import com.basejava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume resume = new Resume("Name");
        Field field = resume.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "new uuid");
        System.out.println(resume);

        // TODO через отражение (reflection) нужно вызвать resume.toString

        Method method = resume.getClass().getDeclaredMethod("toString");
        method.setAccessible(true);
        System.out.println(method.invoke(resume));
    }
}
