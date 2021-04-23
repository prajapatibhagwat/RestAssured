package files;

public class PayLoad {

	public static String getAddPlaceData() {
		String addPlace = "{" +

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

		return addPlace;
	}

	/**
	 * To Return string as json for adding a book
	 * @param isbn
	 * @param aisle
	 * @return
	 */
	public static String Addbook(String isbn, String aisle) {
		return "{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\""+isbn+"\":\"bcd\",\r\n\""+aisle+"\":\"227\",\r\n\"author\":\"John foe\"\r\n}\r\n";
	}

}
