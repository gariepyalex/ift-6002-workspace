package ca.ulaval.ift6002.m2.file;

import java.util.List;

public interface FileParser<E> {

    List<E> parse();
}
