package stos.experiments.javalang.functional.utilclasses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public
class Person {
    private final String name;
    private final int age;

    public int ageDifference(Person other) {
        return this.age - other.age;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", name, age);
    }
}
