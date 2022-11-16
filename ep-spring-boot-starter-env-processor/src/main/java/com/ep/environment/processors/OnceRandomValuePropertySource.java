package com.ep.environment.processors;

import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * `oncerandom.` acts alike random. but caches results
 */
public class OnceRandomValuePropertySource extends PropertySource<Random> {

    public static final String ONCE_RANDOM_PROPERTY_SOURCE_NAME = "oncerandom";
    private static final String PREFIX = "oncerandom.";
    private final RandomValuePropertySource randomValuePropertySource;
    private ConcurrentMap<String, Object> cache = new ConcurrentHashMap<>();

    public OnceRandomValuePropertySource(String name) {
        super(name, new Random());
        randomValuePropertySource = new RandomValuePropertySource();
    }

    public OnceRandomValuePropertySource() {
        this(ONCE_RANDOM_PROPERTY_SOURCE_NAME);
    }

    @Override
    public Object getProperty(String name) {
        if (!name.startsWith(PREFIX)) {
            return null;
        }
        return cache.computeIfAbsent(name, key -> randomValuePropertySource.getProperty(key.replace(PREFIX, "random.")));
    }
}

