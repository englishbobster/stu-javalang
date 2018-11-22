package stos.experiments.javalang.functional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class StringAndCharFunctionsTest {

    private static final String EXPECTED_CHARVALS_AS_STRING = "98 48 114 107";
    private static final String EXPECTED_CHARS_AS_STRING = "b 0 r k";
    private static final String EXPECTED_CHARS_AS_STRING_NO_DIGITS = "b r k";
    private static final String TESTSTRING = "b0rk";

    @Test
    void should_print_char_values_in_a_string() {
        assertThat(StringAndCharFunctions.streamCharValuesToString(TESTSTRING), is(EXPECTED_CHARVALS_AS_STRING));

    }

    @Test
    void should_print_chars_in_a_string() {
        assertThat(StringAndCharFunctions.streamCharsToString(TESTSTRING), is(EXPECTED_CHARS_AS_STRING));
    }

    @Test
    void should_remove_digits() {
        assertThat(StringAndCharFunctions.streamCharsToStringNoDigits(TESTSTRING), is(EXPECTED_CHARS_AS_STRING_NO_DIGITS));
    }
}