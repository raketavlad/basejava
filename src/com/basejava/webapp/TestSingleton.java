package com.basejava.webapp;

import com.basejava.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {

    }

    public enum Singleton {
        INSTANCE
    }




    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.name());
        System.out.println(instance.ordinal());

        for(SectionType type: SectionType.values()) {
            System.out.println(type.getTitle());
        }

    }
}
