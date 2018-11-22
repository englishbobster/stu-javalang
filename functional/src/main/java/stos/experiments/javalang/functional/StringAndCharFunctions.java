package stos.experiments.javalang.functional;

import java.util.stream.Collectors;

class StringAndCharFunctions {

    static String streamCharValuesToString(String str) {
        return str.chars().boxed().map(ch -> Integer.toString(ch)).collect(Collectors.joining(" "));
    }

    static String streamCharsToString(String str) {
        return str.chars().mapToObj(ch -> Character.valueOf((char) ch).toString()).collect(Collectors.joining(" "));
    }

    static String streamCharsToStringNoDigits(String str) {
        return str.chars().mapToObj(ch -> (char) ch).filter(ch -> !Character.isDigit(ch))
                .map(Object::toString).collect(Collectors.joining(" "));
    }
}
