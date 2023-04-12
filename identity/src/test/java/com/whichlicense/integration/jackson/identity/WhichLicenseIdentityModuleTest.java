/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/jackson-integration.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.whichlicense.integration.jackson.identity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WhichLicenseIdentityModuleTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new WhichLicenseIdentityModule());
    }

    @Test
    void givenJSONTestRecordWhenDeserializingThenATestRecordShouldBeReturned() throws JsonProcessingException {
        var json = """
                {
                    "something": "test",
                    "other": 123456,
                    "identity": "9a7743d800000",
                    "identifier": "9a795c1000000",
                    "id": "9a7a508800000"
                }
                """;

        var deserialized = mapper.readValue(json, TestRecord.class);

        assertThat(deserialized)
                .extracting("other", "identity", "identifier", "id")
                .doesNotContainNull()
                .containsExactly(123456L, 2717392480239616L, 2717536420364288L, 2717602044444672L);
    }

    @Test
    void givenTestRecordWhenSerializingThenAValidJSONTestRecordStringShouldBeReturned() throws JsonProcessingException {
        var test = new TestRecord("test", 123456L, 2717392480239616L, 2717536420364288L, 2717602044444672L);
        var serialized = mapper.writeValueAsString(test);

        assertThat(serialized).isEqualToIgnoringWhitespace("""
                {
                    "something": "test",
                    "other": 123456,
                    "identity": "9a7743d800000",
                    "identifier": "9a795c1000000",
                    "id": "9a7a508800000"
                }
                """);
    }

    record TestRecord(String something, long other, long identity, long identifier, long id) {
    }
}
