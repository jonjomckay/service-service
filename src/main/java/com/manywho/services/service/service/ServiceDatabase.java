package com.manywho.services.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import com.manywho.sdk.api.run.elements.type.ListFilter;
import com.manywho.sdk.services.database.ReadOnlyDatabase;
import com.manywho.services.service.ApplicationConfiguration;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ServiceDatabase implements ReadOnlyDatabase<ApplicationConfiguration, Service> {
    private final ObjectMapper objectMapper;

    @Inject
    public ServiceDatabase(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Service find(ApplicationConfiguration configuration, String id) {
        throw new RuntimeException("Loading a single service isn't supported");
    }

    public List<Service> findAll(ApplicationConfiguration configuration, ListFilter listFilter) {
        try {
            return Arrays.asList(objectMapper.readValue(Resources.getResource("services.json"), Service[].class));
        } catch (IOException e) {
            throw new RuntimeException("Unable to read the list of services", e);
        }
    }
}
