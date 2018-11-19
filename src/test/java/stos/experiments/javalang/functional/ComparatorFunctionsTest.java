package stos.experiments.javalang.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stos.experiments.javalang.functional.utilclasses.Person;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComparatorFunctionsTest {

    private List<Person> people;
    private List<Person> peopleWithKen;
    private Person bea;
    private Person noah;
    private Person anna;
    private Person stu;
    private Person ken;

    @BeforeEach
    void setUp() {
        bea = new Person("Bea", 15);
        noah = new Person("Noah", 17);
        anna = new Person("Anna", 49);
        ken = new Person("Ken", 17);
        stu = new Person("Stu", 48);
        people = Arrays.asList(stu, anna, noah, bea);
        peopleWithKen = Arrays.asList(bea, ken, noah, stu, anna);
    }


    @Test
    void should_sort_people_by_age_ascending_order() {
        List<Person> orderedList = Arrays.asList(bea, noah, stu, anna);
        assertTrue(ComparatorFunctions.sortByAgeAscending(people).equals(orderedList));
    }

    @Test
    void should_sort_people_by_age_descending_order() {
        List<Person> orderedList = Arrays.asList(anna, stu, noah, bea);
        assertTrue(ComparatorFunctions.sortByAgeDescending(people).equals(orderedList));

    }

    @Test
    void should_sort_people_by_name_ascending_order() {
        List<Person> orderedList = Arrays.asList(anna, bea, noah, stu);
        assertTrue(ComparatorFunctions.sortByNameAscending(people).equals(orderedList));
    }

    @Test
    void should_sort_people_by_name_descending_order() {
        List<Person> orderedList = Arrays.asList(stu, noah, bea, anna);
        assertTrue(ComparatorFunctions.sortByNameDescending(people).equals(orderedList));
    }

    @Test
    void should_sort_people_by_name_and_then_age_ascending() {
        List<Person> orderedList = Arrays.asList(bea, ken, noah, stu, anna);
        this.peopleWithKen = Arrays.asList(stu, anna, noah, bea, ken);
        assertTrue(ComparatorFunctions.sortByAgeAndThenNameAscending(peopleWithKen).equals(orderedList));
    }
}