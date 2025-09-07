package org.json.json_schema_validator.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class Validator {

    private Validator() {
        // Private constructor to prevent instantiation
    }

    public static Set<ValidationMessage> jsonValidator(String schemaPath, String filePath) throws IOException {
        return jsonValidator(schemaPath, filePath, true);
    }

    public static Set<ValidationMessage> jsonValidator(String schemaPath, String filePath, boolean ignoreDates) throws IOException {
        // Load the schema
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode schemaNode = jsonMapper.readTree(new File(schemaPath));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        JsonSchema schema = factory.getSchema(schemaNode);

        // Load the JSON file to validate
        JsonNode jsonNode = jsonMapper.readTree(new File(filePath));

        // Validate the JSON against the schema
        Set<ValidationMessage> validationResult = schema.validate(jsonNode);

        // Filter out date-related validation messages if ignoreDates is true
        Set<ValidationMessage> filteredValidationResult;
        if (ignoreDates) {
            filteredValidationResult = validationResult.stream()
                    .filter(message -> !message.getMessage().toLowerCase().contains("date"))
                    .collect(Collectors.toSet());
        }
        else {
            filteredValidationResult = validationResult;
        }
        // Print validation messages for debugging
        filteredValidationResult.forEach(message -> System.out.println("Validation Message: " + message));

        return filteredValidationResult;
    }
}
