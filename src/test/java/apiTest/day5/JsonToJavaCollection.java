package apiTest.day5;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
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

    @Test
    public void test2() {
        Response response = get("api/profile");
       assertEquals(response.statusCode(),200);


       // de-serialization Json to JAVA Collection(Map)

        List<Map<String ,Object>> allBody =response.body().as(List.class);
        System.out.println("allBody = " + allBody);

        //print and verify name of the company as "Microsoft"
        System.out.println("allBody.get(4).get(\"company\") = " + allBody.get(4).get("company"));
        String expectedCompany="Microsoft";
        String actualCompany = (String) allBody.get(4).get("company");
        assertEquals(actualCompany,expectedCompany);

        Map<String, Object> secondUserInfo = allBody.get(1);
        System.out.println("secondUserInfo = " + secondUserInfo);


    }

    @Test
    public void getOneUser() {
        Response response = get("api/profile");
        assertEquals(response.statusCode(),200);

        List<Map<String ,Object>> allBody =response.body().as(List.class);

        //verify eurotech users's details

        Map<String,Object> userEurotech= (Map<String, Object>) allBody.get(263).get("user");
        System.out.println("userEurotech = " + userEurotech);

        assertEquals(userEurotech.get("id"),528.0);
        assertEquals(userEurotech.get("email"),"eurotech@gmail.com");
        assertEquals(userEurotech.get("name"),"Teacher");

        //
       // assertEquals(userEurotech.get("company"),"Eurotech Study"); //null

        String  actualCompany = (String) allBody.get(263).get("company");
        System.out.println("company = " + actualCompany);
        assertEquals(actualCompany,"Eurotech Study");


    }
}
