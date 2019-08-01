package ioAssured;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.jsv.JsonSchemaValidatorSettings;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class SchemaValidation {
	
	public static void main(String[] args) {
		JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
				  .setValidationConfiguration(
				   ValidationConfiguration.newBuilder()
				    .setDefaultVersion(SchemaVersion.DRAFTV4)
				      .freeze()).freeze();
				JsonSchemaValidator.settings = JsonSchemaValidatorSettings.settings()
				  .with().jsonSchemaFactory(factory)
				      .and().with().checkedValidation(false);
		
		
		
		Response resp =  RestAssured.given()
				.baseUri("http://localhost:8181")
				.basePath("/web/user.json")
			    .get();
		
		ValidatableResponse validatableResponse = resp.then().assertThat()
			      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user.json"));		
		
		System.out.println(validatableResponse.extract().statusLine());
	}

}
