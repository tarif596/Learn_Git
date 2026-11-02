package restassuredpractice;
import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourses;

public class OAuthTest {
      public static void main(String args[])
      {
    	  String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
    	  .formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
    	  .formParam("grant_type", "client_credentials")
    	  .formParams("scope", "trust")
    	  .when().log().all()
    	  .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
    	  
    	  System.out.println(response);
    	  
    	  JsonPath js = new JsonPath(response);
    	  String accessToken = js.getString("access_token");
    	  
//    	  String response_2 = given().queryParam("access_token", accessToken)
//    	  .when().log().all()
//          .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
//    	  
//    	  System.out.println(response_2);
    	  
    	  GetCourses gc = given().queryParam("access_token", accessToken)
    	    	  .when().log().all()
    	          .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourses.class);
    	  
    	  System.out.println(gc.getInstructor());
    	  System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
    	  
    	  List<Api> apiCourses = gc.getCourses().getApi();
    	  
          for(int i = 0; i < apiCourses.size(); i++)
          {
        	  System.out.println(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("Rest Assured Automation using Java"));
          }
      }

}