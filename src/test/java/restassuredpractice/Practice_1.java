package restassuredpractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import payloads.Payload;
import resuablemethods.rawToJson;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import static io.restassured.RestAssured.*;

public class Practice_1 {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace())
		.when().post("maps/api/place/add/json?key=qaclick123")
		.then().assertThat().log().all().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath j = rawToJson.stringToJSON(response);
		String placeId = j.getString("place_id");
		
		System.out.println(placeId);
		
		String newAddress = "70 winter walk, Germany";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.put("maps/api/place/update/json").then().assertThat().log().all().body("msg", equalTo("Address successfully updated"));
		
		
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath jsonpath = rawToJson.stringToJSON(getPlaceResponse);
		
		String actualAdress = jsonpath.getString("address");
		
		System.out.println(actualAdress);
		
		Assert.assertEquals(actualAdress, newAddress);
		
	}

}
