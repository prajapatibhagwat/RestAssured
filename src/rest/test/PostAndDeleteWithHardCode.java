package rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostAndDeleteWithHardCode {

	
	
	@Test
	public void addAndDelete() {
		
		RestAssured.baseURI ="http://216.10.245.166";
		
		String bodyParam = "{" +

						"\"location\": {" +

						"\"lat\": -33.8669710," +

						"\"lng\": 151.1958750" +

						"}," +

						"\"accuracy\": 50," +

						"\"name\": \"Google Shoes!\"," +

						"\"phone_number\": \"(02) 9374 4000\"," +

						"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\"," +

						"\"types\": [\"shoe_store\"]," +

						"\"website\": \"http://www.google.com.au/\"," +

						"\"language\": \"en-AU\"" +

						"}";
		
		Response response = given()
		.queryParam("key", "qaclick123")
		.body(bodyParam)
		.when()
		.post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and()
		.body("status", equalTo("OK"))
		.extract().response();
		
		String responseString = response.asString();
		JsonPath jsonString = new JsonPath(responseString);
		String placeId = jsonString.get("place_id");
		System.err.println(placeId);
	
		//Deleting the place that we added just now
		
		given()
		.queryParam("key", "qaclick123")
		.body("{"+
		"\"place_id\" :\""+placeId+"\""+
				"}")
		.when()
		.post("/maps/api/place/delete/json")
		.then()
		.assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().body("status", equalTo("OK"));
	
	}
}
