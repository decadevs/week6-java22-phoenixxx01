package org.example.models;
import lombok.Getter;
import org.example.enums.Gender;
import org.example.enums.Role;
@Getter

public class Person {
    private String fullName;
    private int age;
    private String id;
    private Gender gender;
    private Role role;




    //Constructors
    public Person(String fullName, int age, String id, Gender gender,Role role) {
        this.fullName = fullName;
        this.age = age;
        this.id = id;
        this.gender = gender;
        this.role = role;
    }

    //GETTERS

    //TO STRING
    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", gender=" + gender +
                ", role=" + role +
                '}';
    }
}