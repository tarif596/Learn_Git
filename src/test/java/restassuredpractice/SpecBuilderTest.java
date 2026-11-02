package restassuredpractice;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo2.AddPlace;
import pojo2.Location;

public class SpecBuilderTest {

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
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		
		ResponseSpecification respsec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(obj);
		
		Response response = res.when().post("maps/api/place/add/json")
		.then().spec(respsec).extract().response();
		
		
		String responseString = response.asString();
		System.out.println(responseString);

	}

}
