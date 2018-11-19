package stos.experiments.javalang.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stos.experiments.javalang.functional.utilclasses.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparatorFunctionsTest {

    private List<Person> people;
    private Person bea;
    private Person noah;
    private Person anna;
    private Person stu;

    @BeforeEach
    void setUp() {
        bea = new Person("Bea", 15);
        noah = new Person("Noah", 17);
        anna = new Person("Anna", 49);
        stu = new Person("Stu", 48);
        people = Arrays.asList(stu, anna, noah, bea);
    }


    @Test
    void should_sort_people_by_age_ascending_order() {
        List<Person> orderdList = Arrays.asList(bea, noah, stu, anna);
        assertTrue(ComparatorFunctions.sortByAgeAscending(people).equals(orderdList));
    }

    @Test
    void should_sort_people_by_age_descending_order() {
        List<Person> orderdList = Arrays.asList(anna, stu, noah, bea);
        assertTrue(ComparatorFunctions.sortByAgeDescending(people).equals(orderdList));

    }
}