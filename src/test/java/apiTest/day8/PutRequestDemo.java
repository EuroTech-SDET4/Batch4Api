package apiTest.day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class PutRequestDemo {

    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }


    @Test
    public void addNewExperiences() {

        /*
        {
  "title": "string",
  "company": "string",
  "location": "string",
  "from": "YYYY-MM-DD",
  "to": "YYYY-MM-DD",
  "current": false,
  "description": "string"
}
         */
        Map<String,Object> experienceBody= new HashMap<>();
        experienceBody.put("title","Manager");
        experienceBody.put("company","DHL");
        experienceBody.put("location","London");
        experienceBody.put("from","2004-10-10");
        experienceBody.put("to","2005-10-10");
        experienceBody.put("current",false);
        experienceBody.put("description","Very well");

        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",Authorization.getToken())
                .and()
                .body(experienceBody)
                .when()
                .post("/api/profile/experience")
                .then().assertThat().statusCode(201);


    }

    @Test
    public void updateExperiencesPutMethod() {

        Map<String,Object> experienceBody= new HashMap<>();
        experienceBody.put("title","Manager");
        experienceBody.put("company","Volkswagen");
        experienceBody.put("location","Manchester");
        experienceBody.put("from","2004-10-10");
        experienceBody.put("to","2005-10-10");
        experienceBody.put("current",false);
        experienceBody.put("description","Very well");

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("eurotech@gmail.com","Test12345!"))
                .and()
                .body(experienceBody)
                .when()
                .put("/api/profile/experience/285")
                .then()
                .log().all()
                .assertThat().statusCode(204);

    }

    @Test
    public void updateExperiencesWithPatchMethod() {

        Map<String,Object> experienceBody= new HashMap<>();
        experienceBody.put("title","Developer");
        experienceBody.put("company","BMW");

        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .headers(Authorization.getAccessToken("eurotech@gmail.com","Test12345!"))
                .and()
                .pathParam("id",240)
                .and()
                .body(experienceBody)
                .when()
                .patch("api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(204);
    }

    @Test
    public void deleteExperience() {

        given().log().all()
                .and().contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",Authorization.getToken())
                .and()
                .pathParam("id",282)
                .and()
                .delete("api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(200);

    }
}
