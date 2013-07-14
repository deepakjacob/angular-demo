package com.dj.ngapp.core.servlet;

import com.dj.ngapp.core.http.AppResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Deserialize JSON responses from the client.
 *
 * @author Deepak Jacob
 */
public class AppResponseDeserializer extends JsonDeserializer<AppResponse> {

    private Logger logger = LoggerFactory.getLogger(AppResponseDeserializer.class);

    @Override
    public AppResponse deserialize(final JsonParser jp, final DeserializationContext ctxt)
            throws IOException,
            JsonProcessingException {

        return deserializeInternal(jp, ctxt);
    }

    private AppResponse deserializeInternal(final JsonParser jp, final DeserializationContext ctxt) {
        try {
            String text = jp.getText();
            logger.debug("Parsed text from JSON - {}", text);
        } catch (JsonParseException e) {
            throw new RuntimeException("JSON Parsing failed ", e);
        } catch (IOException e) {
            throw new RuntimeException("JSON Parsing failed ", e);
        }
        return null;
    }

}
