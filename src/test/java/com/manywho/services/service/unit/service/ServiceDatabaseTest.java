package com.manywho.services.service.unit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.manywho.sdk.api.jackson.ObjectMapperFactory;
import com.manywho.services.service.service.Service;
import com.manywho.services.service.service.ServiceDatabase;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceDatabaseTest {
    private final ServiceDatabase database;

    public ServiceDatabaseTest() {
        this.database = new ServiceDatabase(ObjectMapperFactory.create());
    }

    @Test(expected = RuntimeException.class)
    public void testFind() {
        database.find(null, null);
    }

    @Test
    public void testFindAll() {
        List<Service> result = database.findAll(null, null);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertThat(result, hasItem(hasProperty("id", equalTo("service-1"))));
        assertThat(result, hasItem(hasProperty("name", equalTo("Service"))));
        assertThat(result, hasItem(hasProperty("description", equalTo("An example service"))));
        assertThat(result, hasItem(hasProperty("icon", equalTo("icon.png"))));
        assertThat(result, hasItem(hasProperty("uri", equalTo("https://services.manywho.com/api/service/1"))));
        assertThat(result, hasItem(hasProperty("version", equalTo("1"))));

        assertThat(result, hasItem(hasProperty("id", equalTo("service-2"))));
        assertThat(result, hasItem(hasProperty("name", equalTo("Service 2"))));
        assertThat(result, hasItem(hasProperty("description", equalTo("Another example service"))));
        assertThat(result, hasItem(hasProperty("icon", equalTo("icon-2.png"))));
        assertThat(result, hasItem(hasProperty("uri", equalTo("https://services.manywho.com/api/service/2"))));
        assertThat(result, hasItem(hasProperty("version", equalTo("1"))));
    }

    @Test(expected = RuntimeException.class)
    public void testFindAllWithJsonException() throws IOException {
        ObjectMapper objectMapper = mock(ObjectMapper.class);

        when(objectMapper.readValue(any(URL.class), eq(Service[].class)))
                .thenThrow(new IOException("Some JSON exception"));

        ServiceDatabase database = new ServiceDatabase(objectMapper);

        database.findAll(null, null);
    }
}
