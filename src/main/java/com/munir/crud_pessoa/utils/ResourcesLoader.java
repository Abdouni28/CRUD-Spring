package com.munir.crud_pessoa.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public final class ResourcesLoader {

    private ResourcesLoader() {}

    public static String loadResourceAsString(String path) {

        try {

            Resource resource = new ClassPathResource(path);

            return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {

            throw new RuntimeException("Erro ao carregar resource: " + path, e);
        }
    }
}