package stos.experiments.javalang.file_reading.integer_to_array;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class FileToArrayReaderTest {
    private static final String TEST_STRING = "1\n2\n3\n4\n5\n6\n7";

    @Test
    void should_read_a_file_containing_integers() {
        FileToArrayReader readArray = new FileToArrayReader(new StringReader(TEST_STRING));
        Integer[] result = readArray.readArray();
        assertThat(result, arrayContaining(1,2,3,4,5,6,7));
    }
}
