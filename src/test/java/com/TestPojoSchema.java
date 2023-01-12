package com;

import apiTest.day6.EurotechUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static org.testng.Assert.assertEquals;

public class TestPojoSchema {
    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void oneUserEurotech(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 528)
                .when().get("api/profile/userQuery");
        System.out.println("response.statusCode() = " + response.statusCode());

        //JSON to our Eurotech class object
        DevExPojo oneUser= response.body().as(DevExPojo.class);




        //print all information
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getName() = " + oneUser.getName());
        System.out.println("oneUser.getProfileId() = " + oneUser.getProfileId());
        System.out.println("oneUser.getStatus() = " + oneUser.getStatus());

        //verify all information
        assertEquals(oneUser.getId(),528.0);
        assertEquals(oneUser.getName(),"Teacher");
        assertEquals(oneUser.getEmail(),"eurotech@gmail.com");
        assertEquals(oneUser.getCompany(),"Eurotech Study");
        assertEquals(oneUser.getProfileId(),276.0);
        assertEquals(oneUser.getStatus(),"Instructor");


    }
}

