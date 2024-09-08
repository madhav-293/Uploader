package com.CSV.CsvReader.validator;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class UserValidator {

    private Schema schema;

    public UserValidator() {
        try (InputStream schemaStream = getClass().getResourceAsStream("/schema/user-schema.json")) {
            assert schemaStream != null;
            JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStream));
            schema = SchemaLoader.load(jsonSchema);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON Schema", e);
        }
    }

    public void validate(String jsonString) {
        JSONObject jsonSubject = new JSONObject(new JSONTokener(jsonString));
        schema.validate(jsonSubject); // Validate the JSON against the schema
    }
}
