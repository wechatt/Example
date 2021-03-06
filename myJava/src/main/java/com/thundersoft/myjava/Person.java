package com.thundersoft.myjava;

public class Person {
    String name ;
    int age;

    public Person(String name,int age){
        this.name=name;
        this.age=age;
    }

    @Override
    public int hashCode() {
        return name.hashCode()+age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)){
             return false;
        }
        Person person = (Person)obj;
        return this.name.equals(person.name) && this.age == person.age;
    }

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
}
