/*
 * Copyright (c) 2023 - for information on the respective copyright owner
 * see the NOTICE file and/or the repository https://github.com/whichlicense/jackson-integration.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

open module whichlicense.integration.jackson.identification {
    requires whichlicense.identification.license;
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    exports com.whichlicense.integration.jackson.identification;
    exports com.whichlicense.integration.jackson.identification.pipeline;
}
