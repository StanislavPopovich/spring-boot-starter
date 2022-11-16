package com.ep.environment.processors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.RandomValuePropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

public class OnceRandomValuePropertySourcePostProcessor implements EnvironmentPostProcessor, Ordered {

    private final int order = ConfigFileApplicationListener.DEFAULT_ORDER + 1;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addAfter(RandomValuePropertySource.RANDOM_PROPERTY_SOURCE_NAME,
                new OnceRandomValuePropertySource());
    }

    @Override
    public int getOrder() {
        return order;
    }
}

