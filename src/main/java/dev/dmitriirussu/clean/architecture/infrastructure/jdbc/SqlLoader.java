package dev.dmitriirussu.clean.architecture.infrastructure.jdbc;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class SqlLoader {

    private static final Map<String, String> CACHE = new ConcurrentHashMap<>();

    private SqlLoader() {}

    static String load(String classpathLocation) {
        return CACHE.computeIfAbsent(classpathLocation, SqlLoader::read);
    }

    private static String read(String classpathLocation) {
        try {
            return new ClassPathResource(classpathLocation)
                    .getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException(
                    "Failed to load SQL resource: " + classpathLocation, e);
        }
    }
}
