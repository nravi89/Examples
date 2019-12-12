package com.ravi;

import org.testng.annotations.BeforeTest;

import com.ravi.pojo.Student;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StudentTest {
	
	@BeforeTest
	private void setup() {
		Student s1 = new Student("Ravi narayan", 32, "Btech");
		Student s2 = new Student("Swati Verma", 28, "Mtech");
		Response resp = RestAssured.given()
				                   .contentType(ContentType.JSON)
				                   .body(s1)
				                   .post("http://localhost:4356/student");
		
	}

}
