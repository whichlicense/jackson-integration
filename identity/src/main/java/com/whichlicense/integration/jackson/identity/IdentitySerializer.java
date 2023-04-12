/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/jackson-integration.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.integration.jackson.identity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.whichlicense.metadata.identity.Identity;

import java.io.IOException;
import java.util.Set;

/**
 * Custom JSON serializer for converting long values representing identities to JSON values.
 * This serializer extends the {@link JsonSerializer} class and provides custom logic for
 * serializing {@link Long} values to JSON values using the {@link Identity#toHex(long)}
 * method if the current JSON property name in the output context matches predefined
 * identity-related property names ("identity", "identifier", or "id"), otherwise, it
 * falls back to serializing the value as a regular long value using
 * {@link JsonGenerator#writeNumber(long)}.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class IdentitySerializer extends JsonSerializer<Long> {
    /**
     * Serializes a long value representing an identity to a JSON value.
     *
     * @param value   the long value representing the identity
     * @param gen     the {@link JsonGenerator} used for generating JSON output
     * @param ignored the {@link SerializerProvider} (ignored)
     * @throws IOException if an I/O error occurs during serialization
     * @since 0.0.0
     */
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider ignored) throws IOException {
        if (Set.of("identity", "identifier", "id").contains(gen.getOutputContext().getCurrentName())) {
            gen.writeString(Identity.toHex(value));
        } else {
            gen.writeNumber(value);
        }
    }
}
