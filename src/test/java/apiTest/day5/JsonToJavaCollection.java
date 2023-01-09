package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;

public class JsonToJavaCollection {

    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 528)
                .when().get("api/profile/userQuery");

        assertEquals(response.statusCode(),200);

        //converting json to java
        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);


    }
}
