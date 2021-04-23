package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class CommonUtilities {

	/**
	 * TO convert into xmlpath and return it back
	 * @return 
	 */
	public static XmlPath rawToXml(Response res) {
		String response = res.asString();
		XmlPath xmlPath = new XmlPath(response);
		return xmlPath;
	}
	
	/**
	 * TO convert into Jsonpath and return it back
	 * @return 
	 */
	public static JsonPath rawToJson(Response res) {
		String response = res.asString();
		JsonPath jsonPath = new JsonPath(response);
		return jsonPath;
	}
}
