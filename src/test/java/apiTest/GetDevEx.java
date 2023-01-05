package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class GetDevEx {

    String devExUrl="http://92.205.106.232";


    @Test
    public void test1() {

        /*
        Given accept type is Json
        When user send GETS request to /api/profile
        Then verify that response status code is 200
        and verify that body contains "Microsoft"
         */
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");

        //verify that status code 200
        assertEquals(response.statusCode(),200);

        //verify that body has Microsoft
        assertTrue(response.body().asString().contains("Microsoft"));


        //Break till : 18:30 UK time

    }

    @Test
    public void headersTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");

        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"ETag\") = " + response.header("ETag"));

        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");

        assertTrue(response.headers().hasHeaderWithName("Date"));




    }
}
