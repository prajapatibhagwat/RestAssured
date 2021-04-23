package rest.test;

import static io.restassured.RestAssured.given;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.CommonUtilities;
import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DynamicJson {

	@Test(dataProvider = "BooksData")

	public void addBook(String isbn, String aisle)

	{

		RestAssured.baseURI = "http://216.10.245.166";
		Response resp = given().

				header("Content-Type", "application/json").

				body(PayLoad.Addbook(isbn, aisle)).

				when().

				post("/Library/Addbook.php").

				then().assertThat().statusCode(200).

				extract().response();

		JsonPath js = CommonUtilities.rawToJson(resp);

		String id = js.get("ID");

		System.out.println(id);

		// deleteBOok

	}

	@DataProvider(name = "BooksData")
	public Object[][] getData() {
		return new Object[][] { { "asdfghzxcvbn", "566" }, { "qwasdzx", "34567" } };
	}
	
	public static RequestSpecification objRequestSpecification;

	public static RequestSpecification requestSpecification() throws IOException {
		if (objRequestSpecification == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			objRequestSpecification = new RequestSpecBuilder().setBaseUri("http://restapi.demoqa.com/utilities/weather/city")
					.addQueryParam("key", "value").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return objRequestSpecification;
		}
		
		return objRequestSpecification;
	}
}
