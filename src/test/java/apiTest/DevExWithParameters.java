package apiTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;

public class DevExWithParameters {


    String devExUrl="http://92.205.106.232";
    String petURL="https://petstore.swagger.io/v2";

//    @BeforeClass
//    public void beforeClass(){
//        baseURI="http://92.205.106.232";
//    }
//
//    @Test
//    public void test1() {
//        Response response = get(baseURI + "/api/profile");
//        response.prettyPrint();
//    }

    @Test
    public void pathParamPetStore() {

        //first way request with path param
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petURL + "/pet/9");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);

    }

    @Test
    public void pathParamPet2() {

        int petID=9;

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("userID", petID)
                .when().get(petURL + "/pet/{userID}");
        assertEquals(response.statusCode(),200);
    }
}
