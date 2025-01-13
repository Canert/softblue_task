package org.canert;

import java.util.Objects;
import java.util.Optional;

public class Person {
    private String myTitle;
    private String myName;
    private String mySurname;
    private Optional<Integer> myAge;

    public String getTitle() {
        return myTitle;
    }

    public void setTitle( String title ) {
        myTitle = title;
    }

    public String getName() {
        return myName;
    }

    public void setName( String name ) {
        myName = name;
    }

    public String getSurname() {
        return mySurname;
    }

    public void setSurname( String surname ) {
        mySurname = surname;
    }

    public Optional<Integer> getAge() {
        return myAge;
    }

    public void setAge( Optional<Integer> age ) {
        myAge = age;
    }

    @Override
    public boolean equals( Object o ) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals( myTitle, person.myTitle ) && Objects.equals( myName, person.myName ) && Objects.equals( mySurname, person.mySurname ) && Objects.equals( myAge, person.myAge );
    }

    @Override
    public int hashCode() {
        return Objects.hash( myTitle, myName, mySurname, myAge );
    }

    @Override
    public String toString() {
        // I hope that's ok...
        return "Title=" + myTitle +
                ", Name=" + myName +
                ", Age=" + ( myAge.isPresent() ? myAge.get() : " " );
    }
}

