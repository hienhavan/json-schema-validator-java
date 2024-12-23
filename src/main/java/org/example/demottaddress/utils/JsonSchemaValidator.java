package org.example.demottaddress.utils;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.github.fge.jsonschema.core.report.ProcessingReport;
//import com.github.fge.jsonschema.main.JsonSchema;
//import com.github.fge.jsonschema.main.JsonSchemaFactory;
//import lombok.SneakyThrows;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JsonSchemaValidator {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final JsonSchema jsonSchema;
//    @SneakyThrows
//    public JsonSchemaValidator() {
//        JsonNode schemaNode = objectMapper.readTree(getClass().getResourceAsStream("/validator/user-schema.json"));
//        jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
//    }
//
//    public boolean validateJson(String json) {
//        try {
//            JsonNode jsonNode = objectMapper.readTree(json);
//            ProcessingReport report = jsonSchema.validate(jsonNode);
//            return report.isSuccess();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class JsonSchemaValidator {

    private final JsonSchema jsonSchema;

    @SneakyThrows
    public JsonSchemaValidator() {
        JsonNode schemaNode = JsonSchemaLoader.loadSchema();
        jsonSchema = JsonSchemaFactory.byDefault().getJsonSchema(schemaNode);
    }

    @SneakyThrows
    public boolean validateJson(String json) {
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(json);
            ProcessingReport report = jsonSchema.validate(jsonNode);
            return report.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

