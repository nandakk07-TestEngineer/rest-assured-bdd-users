package com.automation.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SchemaValidator {

	private static final Logger log = LogManager.getLogger(SchemaValidator.class);
	private static final String SCHEMA_PATH = "schemas/";
	
	private SchemaValidator() {}
	
	public static void validateSchema(Response response, String schemaFile) {
		log.info("Validating response against :", schemaFile);
		response.then()
			.assertThat()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(SCHEMA_PATH + schemaFile));
		log.info("Schema Validation Passed",schemaFile);
	}
	
	public static void assertFieldNotEmpty(Response response,String jsonPath) {
		String value = response.jsonPath().getString(jsonPath);
		assertThat(value)
			.isNotNull()
			.isNotEmpty();
	}
}
