package org.canert;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class PersonService {

    private final static String MY_PREFIX = "Mr.";

    public static void main( String[] args ) {
        PersonService m = new PersonService();
        // 1.
        Collection<Person> people = m.generateTwelvePeople();
        // 2.
        m.printThreeOldestPeople( people );
        // 3.
        m.groupByTitle( people );
        // 4.
        m.mergeTwoPeopleLists(people, people);
    }

    public List<Person> generateTwelvePeople() {
        Faker faker = new Faker();
        List<Person> peopleList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            Person person = new Person();
            person.setTitle( faker.name().prefix() );
            person.setName( faker.name().firstName() );
            person.setSurname( faker.name().lastName() );
            // setting age for first seven people
            if (i < 7) {
                person.setAge( Optional.of(  random.nextInt(80) + 20 ) );
            } else {
                person.setAge( Optional.empty() );
            }
            peopleList.add( person );
        }
        return peopleList;
    }

    public List<Person> printThreeOldestPeople( Collection<Person> people) {
        if (people == null) {
            System.out.println("no valid people list provided");
            return null;
        }

        // sorting by age
        List<Person> sortedPeopleByAgeDescAndLimited = people.stream().sorted( Comparator.comparing( o1 -> {
            Person p1 = (Person) o1;
            if (p1.getAge().isPresent()) {
                return p1.getAge().get();
            } else {
                return 0;
            }
        } ).reversed() ).limit( 3 ).toList();

        // printing
        sortedPeopleByAgeDescAndLimited.forEach( p -> System.out.println(p.toString() ) );

        return sortedPeopleByAgeDescAndLimited;
    }

    public Map<String, List<Person>> groupByTitle(Collection<Person> people) {
        if (people == null) {
            System.out.println("no valid people list provided");
            return null;
        }

        // grouping
        Map<String, List<Person>> groupedPeople = people.stream().collect( groupingBy(Person::getTitle) );

        // printing
        groupedPeople.values()
                .forEach( v -> System.out.println(v.getFirst().getTitle() + " group size = " + v.size()) );

        // modifying
        groupedPeople.get( MY_PREFIX ).forEach( p -> p.setName( "Pan " + p.getName() ) );

        return groupedPeople;
    }

    public List<Person> mergeTwoPeopleLists(Collection<Person> list1, Collection<Person> list2) {
        return Stream.concat( list1.stream(), list2.stream() ).distinct().toList();
    }
}