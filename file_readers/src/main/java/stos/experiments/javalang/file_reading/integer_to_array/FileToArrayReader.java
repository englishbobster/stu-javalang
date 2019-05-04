package stos.experiments.javalang.file_reading.integer_to_array;

import java.io.Reader;

class FileToArrayReader {

    Reader reader;


    FileToArrayReader(Reader reader) {
        this.reader = reader;
    }

    public Integer[] readArray() {
        return new Integer[0];
    }
}
