/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.asciidoctor;

import org.apache.commons.io.IOUtils;
import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Postprocessor;

import java.nio.charset.Charset;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class CodeTabPostprocessor extends Postprocessor {

    @Override
    public String process(final Document document, final String output) {
        final String css = readToString("/tab.css");
        final String javascript = readToString("/tab.js");
        final String replacement = String.format("<style>%n%s%n</style>%n" +
                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/zepto/1.2.0/zepto.min.js\"></script>%n" +
                "<script type=\"text/javascript\">%n%s%n</script>%n</head>%n", css, javascript);
        return output.replace("</head>", replacement);
    }

    private String readToString(final String name) {
        try {
            return IOUtils.toString(getClass().getResourceAsStream(name), Charset.defaultCharset());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
