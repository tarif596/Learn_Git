package resuablemethods;

import io.restassured.path.json.JsonPath;

public class rawToJson {
	
	public static JsonPath stringToJSON(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;

	}
}
