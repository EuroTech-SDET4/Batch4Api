package apiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DevExBodyTest {

    String devExUrl="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";

    @Test
    public void devExVerifyWithPath() {

        /*
                     Verify that all information in the body
                        "id": 531,
                        "email": "berlin@gmail.com",
                       "name": "Babamyrat",
                       "company": "SkyHire Company",
                       "status": "Student or Learning",
                         "profileId": 291
}
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", "531")
                .when().get(devExUrl + "/api/profile/userQuery");

        assertEquals(response.statusCode(),200);

        response.prettyPrint();

        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"status\") = " + response.path("status"));
        System.out.println("response.path(\"profileId\") = " + response.path("profileId"));

        int actualId= response.path("id");

        assertEquals(actualId,531);
        assertEquals(response.path("email"),"berlin@gmail.com");
        assertEquals(response.path("name"),"Babamyrat");
        assertEquals(response.path("company"),"SkyHire Company");
        assertEquals(response.path("status"),"Student or Learning");




    }

    @Test
    public void petStoreVerifyWithPath() {

        /*
        Given accept content type Json
        And path param is 9
        When user GET s request to /pet/9
        Then verify response code is 200
        verify id is 9
        verify name is cats
         */


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 9)
                .when().get(petURL + "/pet/{id}");
        assertEquals(response.statusCode(),200);

        response.prettyPrint();

        int actualId=response.path("id");

        //verify id is 9
        assertEquals(actualId,9);

        System.out.println("response.path(\"name\") = " + response.path("category.name"));

        //verify name is cats
        assertEquals(response.path("category.name"),"cats");

        //verify status is sold
        assertEquals(response.path("status"),"sold");

    }
}
