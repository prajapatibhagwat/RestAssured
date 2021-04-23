package rest.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.PayLoad;
import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostAndDelete {

	Properties prop = new Properties();
	
	@BeforeTest
	public void loadDat() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addAndDelete() {
		
		RestAssured.baseURI = prop.getProperty("host");
		
		Response response = given()
		.queryParam("key", prop.getProperty("KEY"))
		.body(PayLoad.getAddPlaceData()).log().all()
		.when()
		.post(Resources.getAddPlaceResource())
		.then().log().all().assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and()
		.body("status", equalTo("OK"))
		.extract().response();
		
		String responseString = response.asString();
		System.out.println(responseString);
		JsonPath jsonString = new JsonPath(responseString);
		String placeId = jsonString.get("place_id");
		System.err.println(placeId);
	
		//Deleting the place that we added just now
		
		given()
		.queryParam("key", prop.getProperty("KEY"))
		.body("{"+
		"\"place_id\" :\""+placeId+"\""+
				"}")
		.when()
		.post(Resources.getDeletePlaceResource())
		.then()
		.assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().body("status", equalTo("OK"));
	}
}
