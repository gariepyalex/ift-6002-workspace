package ca.ulaval.ift6002.m2.file.reader;

import java.util.List;

public interface FileReader<T> {

    List<T> readAll(String filename);
}
