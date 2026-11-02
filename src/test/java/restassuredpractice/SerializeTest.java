package restassuredpractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo2.AddPlace;
import pojo2.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;



public class SerializeTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		AddPlace obj = new AddPlace();
		obj.setAccuracy(50);
		obj.setAddress("29, side layout, Rajabazar 09");
		obj.setLanguage("French-IN");
		obj.setPhone_number("(+91) 983 893 3937");
		obj.setName("Frontline house");
		obj.setWebsite("Frontline house");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		obj.setTypes(myList);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		obj.setLocation(l);
		
		Response res = given().queryParam("key", "qaclick123")
		.body(obj)
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		
		String response = res.asString();
		System.out.println(response);

	}

}
