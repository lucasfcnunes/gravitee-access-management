/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.am.management.handlers.management.api.mapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import io.gravitee.am.model.Domain;
import io.gravitee.am.model.common.event.Event;
import io.gravitee.am.model.jose.ECKey;
import io.gravitee.am.model.jose.JWK;
import io.gravitee.am.model.jose.OCTKey;
import io.gravitee.am.model.jose.OKPKey;
import io.gravitee.am.model.jose.RSAKey;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.time.Instant;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 */
@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    private final ObjectMapper mapper;

    public ObjectMapperResolver() {
        mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier() {
            @Override
            public JsonDeserializer<Enum> modifyEnumDeserializer(DeserializationConfig config,
                                                                 final JavaType type,
                                                                 BeanDescription beanDesc,
                                                                 final JsonDeserializer<?> deserializer) {
                return new JsonDeserializer<Enum>() {
                    @Override
                    public Enum deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
                        Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                        return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase());
                    }
                };
            }
        });
        module.addSerializer(Enum.class, new StdSerializer<>(Enum.class) {
            @Override
            public void serialize(Enum value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
                jgen.writeString(value.name().toLowerCase());
            }
        });
        module.addSerializer(Instant.class, new StdSerializer<>(Instant.class) {
            @Override
            public void serialize(Instant instant, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeNumber(instant.toEpochMilli());
            }
        });
        module.addDeserializer(JWK.class, new StdDeserializer<>(JWK.class) {
            @Override
            public JWK deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                JsonNode node = jsonParser.getCodec().readTree(jsonParser);
                String kty = node.get("kty").asText();
                if (kty == null) {
                    return null;
                }
                Class<?> keyType =  switch (kty) {
                    case "RSA" -> RSAKey.class;
                    case "EC" -> ECKey.class;
                    case "oct" -> OCTKey.class;
                    case "OKP" -> OKPKey.class;
                    default -> null;
                };
                return keyType != null  ? (JWK) jsonParser.getCodec().treeToValue(node, keyType) : null;
            }
        });
        mapper.addMixIn(Domain.class, MixIn.class);
        mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(module);
        mapper.registerModule(new Jdk8Module());
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }

    private abstract static class MixIn {
        @JsonIgnore
        abstract Event getLastEvent();
    }
}
