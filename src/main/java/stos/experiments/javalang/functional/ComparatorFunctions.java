package stos.experiments.javalang.functional;

import stos.experiments.javalang.functional.utilclasses.Person;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class ComparatorFunctions {

    //(person, other) -> person.ageDifference(other) equivalent to the method reference
    private static final Comparator<Person> AGE_DIFFERENCE_ASCENDING = Person::ageDifference;
    private static final Comparator<Person> AGE_DIFFERENCE_DESCENDING = AGE_DIFFERENCE_ASCENDING.reversed();
    //Remember strings already have an alphabetical compareTo implementation (they implement Comparable) and so we can use that.
    // and then we can replace (person, other) -> person.getName().compareTo(other.getName()) further with "comparing()"
    private static final Comparator<Person> NAME_ASCENDING = Comparator.comparing(Person::getName);
    private static final Comparator<Person> NAME_DESCENDING = NAME_ASCENDING.reversed();

    //using the Comparator interface (its a functional interface)...we can replace the implementation of Compare with
    // a lambda function.
    static List<Person> sortByAgeAscending(List<Person> people) {
        //...yes...the java compiler will understand that
        // (person, other) -> person.ageDifference(other) is equivalent to the method reference!!
        return people.stream().sorted(AGE_DIFFERENCE_ASCENDING).collect(Collectors.toList());
    }

    static List<Person> sortByAgeDescending(List<Person> people) {
        //now we cant rely on using a method reference because
        // (person, other) -> other.ageDifference(person) ....other and person are reversed:-(
        return people.stream().sorted(AGE_DIFFERENCE_DESCENDING).collect(Collectors.toList());
    }

    static List<Person> sortByNameAscending(List<Person> people) {
        return people.stream().sorted(NAME_ASCENDING).collect(Collectors.toList());
    }

    static List<Person> sortByNameDescending(List<Person> people) {
        return people.stream().sorted(NAME_DESCENDING).collect(Collectors.toList());
    }

    //then we can chain our comparators in order of preferred sorting.
    static List<Person> sortByAgeAndThenNameAscending(List<Person> people) {
        return people.stream().sorted(AGE_DIFFERENCE_ASCENDING.thenComparing(NAME_ASCENDING)).collect(Collectors.toList());
    }
}

