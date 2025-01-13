package org.canert;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    PersonService myPersonService = new PersonService();
    List<Person> myList =  myPersonService.generateTwelvePeople();

    private final static String MY_PREFIX = "Mr.";
    private final static String MY_NAME_PREFIX = "Pan";

    @Test
    void testGenerateTwelvePeople() {
        // then
        // checking if size of the output list is 12
        assertEquals( 12, myList.size(),
                "wrong size of the list"  );
        // checking if 5 people have empty age
        assertEquals( 5, myList.stream().filter( p -> p.getAge().isEmpty() ).toList().size(),
                "wrong size of people with empty age" );
    }

    @Test
    void testPrintThreeOldestPeople() {
        // when
        List<Person> sortedList = myPersonService.printThreeOldestPeople( myList);

        // then
        // checking if size of the output list is 3
        assertEquals( 3, sortedList.size(),
                "wrong size of the output list");
    }

    @Test
    void testGroupByTitle() {
        // when
        Map<String, List<Person>> groupedMap = myPersonService.groupByTitle( myList );

        // then
        // checking if all names in MY_PREFIX prefix group have MY_NAME_PREFIX added to names
        groupedMap.get( MY_PREFIX ).forEach( p -> assertEquals( MY_NAME_PREFIX, p.getName().substring( 0, 3 ),
                "wrong prefix" ) );

    }

    @Test
    void testMergeTwoPeopleLists() {
        //given
        List<Person> list2 =  myPersonService.generateTwelvePeople();
        List<Person> listWithDuplicates = new ArrayList<>(myList);

        // when
        listWithDuplicates.add( myList.get( 1 ) );
        List<Person> outputList1 = myPersonService.mergeTwoPeopleLists(listWithDuplicates, myList);
        List<Person> outputList2 = myPersonService.mergeTwoPeopleLists(list2, listWithDuplicates);
        Set<Person> outputSet = new HashSet<>(outputList2);

        // then
        // checking size of the output list
        assertEquals( 12, outputList1.size(),
                "wrong size of the output list" );
        assertEquals( outputSet.size(), outputList2.size(),
                "wrong size of the output list" );
    }
}