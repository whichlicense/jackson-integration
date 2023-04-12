/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/jackson-integration.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.integration.jackson.identity;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Custom Jackson module for serializing and deserializing long values representing identities.
 * This module extends the {@link SimpleModule} class and provides custom serializers and
 * deserializers for {@link Long} values using the {@link IdentitySerializer} and
 * {@link IdentityDeserializer} classes, respectively. It can be registered with a Jackson
 * {@link ObjectMapper} to enable serialization and deserialization of identity values in JSON
 * representations.
 */
public class WhichLicenseIdentityModule extends SimpleModule {
    /**
     * Constructs a new instance of the {@linkplain WhichLicenseIdentityModule}.
     */
    public WhichLicenseIdentityModule() {
        super("WhichLicenseIdentityModule", new Version(0, 0, 0, null, null, null));

        var serializer = new IdentitySerializer();
        addSerializer(Long.class, serializer);
        addSerializer(Long.TYPE, serializer);

        var deserializer = new IdentityDeserializer();
        addDeserializer(Long.class, deserializer);
        addDeserializer(Long.TYPE, deserializer);
    }
}
