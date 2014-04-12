package ca.ulaval.ift6002.m2.file.reader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ByLineFileReader implements FileReader<String> {

    @Override
    public List<String> readAll(String filepath) {
        try {
            URI uri = this.getClass().getResource(filepath).toURI();
            Path path = Paths.get(uri);

            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException | InvalidPathException | URISyntaxException e) {
            // TODO should we stop the program?
            return Collections.emptyList();
        }
    }

}
