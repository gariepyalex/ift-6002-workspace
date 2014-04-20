package ca.ulaval.ift6002.m2.acceptance.fixture;

import ca.ulaval.ift6002.m2.acceptance.DumboTheElephantStories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

public class JsonFixture {
    private ObjectMapper jsonMapper;

    public JsonFixture() {
        jsonMapper = new ObjectMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        jsonMapper.registerModule(module);
    }

    public String convertToJson(Object obj) {
        String json = "";
        try {
            json = DumboTheElephantStories.OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
