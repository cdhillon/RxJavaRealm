package com.cdhillon.rxjavarealm.model;

import java.util.Locale;

import io.realm.RealmObject;

/**
 * Created by cdhillon on 3/18/17.
 */

public class Person extends RealmObject {

    public String name;
    public int age;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%s %d", name, age);
    }
}

