package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequestDemo {

    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void postNewUser() {

        String jsonBody ="{\n" +
                "  \"email\": \"ericcontona@yahoo.com\",\n" +
                "  \"password\": \"Pas1234\",\n" +
                "  \"name\": \"Eric Contona\",\n" +
                "  \"google\": \"EricGoogle\",\n" +
                "  \"facebook\": \"EricFace\",\n" +
                "  \"github\": \"EricGithub\"\n" +
                "}";


        Response response = given().accept(ContentType.JSON) // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        // "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjo1ODZ9LCJpYXQiOjE2NzM1NDU0NTIsImV4cCI6MTY3MzkwNTQ1Mn0.SWfGKfMFUHWAcY8WlHqHnZp02XSdr7T8CWGNY2E04vU"

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser2() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email","jackson@hotmail.com");
        requestMap.put("password","jack12345!");
        requestMap.put("name","Mike Jackson");
        requestMap.put("google","JacksonGoogle");
        requestMap.put("facebook","jacksonFace");
        requestMap.put("github","jacksonGit");



        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser3() {

        NewUserInfo newUserInfo = new NewUserInfo();

        newUserInfo.setEmail("codehouse@hotmail.com");
        newUserInfo.setPassword("code12345!");
        newUserInfo.setName("Paul Roberts");
        newUserInfo.setGoogle("paulGoogle");
        newUserInfo.setFacebook("paulFace");
        newUserInfo.setGithub("paulGit");


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo) //serialization
                .when()
                .post("api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));


    }
}
