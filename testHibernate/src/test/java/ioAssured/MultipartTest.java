package ioAssured;

import io.restassured.http.Header;
import io.restassured.response.Response;

import java.io.File;

import io.restassured.RestAssured;

public class MultipartTest {
	
	public static void main(String[] args) {
		
		File avatarFile = new File("D:/a.jpg");

		Response response = RestAssured.given()
			.baseUri("http://localhost:8181")
			.basePath("SupportSystem/api/testUpload")
			.formParam("issueId", 1)
			.multiPart("file", avatarFile)
			//.header(new Header("Authorization", "Basic cm5hcmF5YW46bkFyYUAxMjM0"))
			.post();
		
		System.out.println(response.getBody().prettyPrint());
	}

}
