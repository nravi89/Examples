package ioAssured;

import java.util.List;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonPathTest {
	
	public static void main(String[] args) {
		Response resp =  RestAssured.given()
									.baseUri("http://localhost:8181")
									.basePath("/web/a.json")
								    .get();
		
		//System.out.println(resp.getBody().prettyPrint());
		
		System.out.println( resp.jsonPath().getJsonObject("dataset[0].attribute"));
		System.out.println( resp.jsonPath().getList("dataset[0].attribute.attributeId[0]").get(0));
		
		System.out.println( resp.jsonPath().get("dataset[0].attribute.attributeId[0]"));
		
		//System.out.println( resp.jsonPath().getList("dataset[0].attribute.source"));
		
		//System.out.println( resp.jsonPath().getList("dataset[0].attribute.findAll {it.attributeId ==13}"));
	}

}
