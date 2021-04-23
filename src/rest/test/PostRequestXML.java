package rest.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.CommonUtilities;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class PostRequestXML {

	@Test
	public void createPlace() throws IOException {
	
		RestAssured.baseURI = "http://216.10.245.166";
		String  bodyData = generateStringFromResurce("C:\\Users\\M1043004\\DevOps\\postdat.xml");
		
		Response res = given().
			queryParam("key", "qaclick123").
					body(bodyData).
					when()
					.post("/maps/api/place/add/xml").
					then().assertThat().statusCode(200).
					and().contentType(ContentType.XML)
					.and().extract().response();
		
		XmlPath xmlPath = CommonUtilities.rawToXml(res);

		String x = xmlPath.get("response.status");
		System.out.println(x);

	
	}
	
	public String generateStringFromResurce(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
}
