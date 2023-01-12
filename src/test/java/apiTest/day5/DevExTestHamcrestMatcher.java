package apiTest.day5;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DevExTestHamcrestMatcher {


    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void getOneUser() {
        /*
        Task
        Given accept content type application json
        And query param id 528
        When user sends GET request to /api/profile/userQuery
        Then status code is 200
  */
        given().accept(ContentType.JSON)
                .queryParam("id", 528)
                .when()
                .get("/api/profile/userQuery")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json");

    }

    @Test
    public void getOneUser_HamcrestMatcher() {

        String expectedEmail = "eurotech@gmail.com";

        given().accept(ContentType.JSON).queryParam("id", 528).when()
                .get("api/profile/userQuery")
                .then().assertThat()
                .statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat()
                .body("id", equalTo(528),
                        "email", Matchers.equalTo(expectedEmail),
                        "name", Matchers.equalTo("Teacher"),
                        "company", Matchers.equalTo("Eurotech Study"),
                        "status", Matchers.equalTo("Instructor"));

        /*
        Given teh user send get request
        Then verify that expected email is "eurotech@gmail.com"



         */
    }

    @Test
    public void hamcrest2() {

        given().accept(ContentType.JSON)
                .queryParam("id", 25)
                .when()
                .log().all()
                .get("api/profile/userQuery")
                .then().assertThat().statusCode(200)
                .and()
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .and()
                .header("ETag", equalTo("W/\"71-gLRrgzE02ZoB4TdrNnm1Irq0Rhc\""))
                .and()
                .header("Date", notNullValue())
                .and()
                .assertThat()
                .body("id", equalTo(25),
                        "name", equalTo("Jr. Dev"),
                        "email", equalTo("jrdev@gmail.com")).log().all();

    }

    @Test
    public void hemCrestBody() {
        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("user.email", hasItem("besktsss@gmail.com"))
                .log().all();
    }

    @Test
    public void hemCrestBodyTest2() {
        //Mike Masters
        //Necip
        //CraigDavid
        //sylvester
        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("user.name", hasItems("sylvester", "CraigDavid", "Necip", "Mike Masters"))
                .log().all();

    }

    @Test
    public void hamCrestBodyTest3() {

        //verify second company is Eurotech
        //verify that second skill's fifth skill is API

        given().accept(ContentType.JSON)
                .when()
                .log().all()
                .get("api/profile")
                .then()
                .assertThat().statusCode(200)
                .and().assertThat().body("company[1]",equalTo("Eurotech"),
                        "skills[1][4]",equalTo("API"));
    }
}