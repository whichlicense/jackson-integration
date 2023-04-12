/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/jackson-integration.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.integration.jackson.identity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.whichlicense.metadata.identity.Identity;

import java.io.IOException;
import java.util.Set;

/**
 * Custom JSON deserializer for converting JSON values to long values representing identities.
 * This deserializer extends the {@link JsonDeserializer} class and provides custom logic for
 * deserializing JSON values to {@link Long} values using the {@link Identity#fromHex(String)}
 * method if the JSON property name matches predefined identity-related property names
 * ("identity", "identifier", or "id"), otherwise, it falls back to deserializing the value as
 * a regular long value using {@link JsonParser#getLongValue()}.
 *
 * @author David Greven
 * @version 0
 * @since 0.0.0
 */
public class IdentityDeserializer extends JsonDeserializer<Long> {
    /**
     * Deserializes a JSON value to a long value representing an identity.
     *
     * @param p       the {@link JsonParser} used for parsing the JSON value
     * @param ignored the {@link DeserializationContext} (ignored)
     * @return the long value representing the deserialized identity
     * @throws IOException if an I/O error occurs during deserialization
     * @since 0.0.0
     */
    @Override
    public Long deserialize(JsonParser p, DeserializationContext ignored) throws IOException {
        if (Set.of("identity", "identifier", "id").contains(p.getParsingContext().getCurrentName())) {
            return Identity.fromHex(p.getValueAsString());
        } else {
            return p.getLongValue();
        }
    }
}
