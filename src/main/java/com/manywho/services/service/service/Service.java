package com.manywho.services.service.service;

import com.manywho.sdk.api.ContentType;
import com.manywho.sdk.services.types.Type;

@Type.Element(name = "Service")
public class Service implements Type {

    @Type.Identifier
    private String id;

    @Type.Property(name = "Name", contentType = ContentType.String)
    private String name;

    @Type.Property(name = "Description", contentType = ContentType.String)
    private String description;

    @Type.Property(name = "URI", contentType = ContentType.String)
    private String uri;

    @Type.Property(name = "Icon", contentType = ContentType.String)
    private String icon;

    @Type.Property(name = "Version", contentType = ContentType.String)
    private String version;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUri() {
        return uri;
    }

    public String getIcon() {
        return icon;
    }

    public String getVersion() {
        return version;
    }
}
