package apiTest.day3;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;


public class PathParameterDevEx {

    String devExUrl="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";

    @Test
    public void pathWithDevEx() {

        /*
        Given accept type is JSON
        When user sends GET request to /api/profile/user/{UserID}
        verify content type
        status code
        id=25
        verify that body has contains jrdev@gmail.com
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", 25)
                .when().get(devExUrl + "/api/profile/user/{userID}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("jrdev@gmail.com"));

    }

    @Test
    public void queryParameterPet() {

         /*            TASK
Given accept type is Json
And query  parameter is status sold
When user sends GET request to /pet/findByStatus
The response status code 200
And content type is application/json
And "cats" should be in payload/ body
 */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("status", "sold")
                .when().get(petURL + "/pet/findByStatus");

        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("cats"));
        assertEquals(response.contentType(),"application/json");

        response.prettyPrint();



    }

    @Test
    public void queryParamDevEx() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("company", "SkyHire Company")
                .when().get(devExUrl + "/api/profile/userQuery");

        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("Babamyrat"));



    }

    @Test
    public void queryParamWithMap() {

        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("status","available");
     //   queryMap.put("status","sold");



        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParams(queryMap)
                .when().get(petURL + "/pet/findByStatus");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
    }
}
