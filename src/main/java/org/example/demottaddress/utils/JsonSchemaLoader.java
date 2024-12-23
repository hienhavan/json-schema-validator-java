package org.example.demottaddress.utils;


import lombok.SneakyThrows;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonSchemaLoader {
    private JsonSchemaLoader() {}

    private static final ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    public static JsonNode loadSchema() {
        String schemaUrl = "https://raw.githubusercontent.com/hienhavan/json-schemas/main/test.json";
        RestTemplate restTemplate = new RestTemplate();

        String schemaContent = restTemplate.getForObject(schemaUrl, String.class);

        return objectMapper.readTree(schemaContent);
    }
}
