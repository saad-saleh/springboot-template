package com.spring.sample.config;


import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageSerializer<T> extends JsonSerializer<PageImpl<T>> {

    @Override
    public void serialize(PageImpl<T> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.getContent());

    }

}
