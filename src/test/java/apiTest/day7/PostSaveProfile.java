package apiTest.day7;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostSaveProfile {

    @BeforeClass

    public void beforeClass() {
        baseURI = "http://92.205.106.232";
    }

    @Test
    public void postNewUser() {

        //Create new user
        //Verify with token
        //Save user profile with using token

        NewUserInfo newUserInfo = new NewUserInfo("emily@usa.com","Password542","Emily","EmilyGoogle","EmilyFace","EmilyGit");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);

        String token = response.path("token");


        String profileBody ="{\n" +
                "    \"status\": \"JAVA, CSS, Javascript\",\n" +
                "    \"skills\": \"SDET\",\n" +
                "    \"company\": \"APPLE\",\n" +
                "    \"bio\": \"thesmith.com.tr\",\n" +
                "    \"website\": \"I am a SDET\",\n" +
                "    \"githubUserName\": \"aliapgus\",\n" +
                "    \"twitter\": \"jjtwitter.com\",\n" +
                "    \"facebook\": \"jjfacebook.com\",\n" +
                "    \"instagram\": \"jjinstagram.com\"\n" +
                "}";

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("api/profile");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();


    }

    @Test
    public void postNewUserAndVerify() {
        String email="chealse@gmail.com";
        String password="Pass8765";
        String name="Andrew Chelsea";
        String google="AndrewGoogle";
        String facebook="Andrew Face";
        String github= "AndrewGit";

        String company ="Samsung";

        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("name",name);
        requestMap.put("google",google);
        requestMap.put("facebook",facebook);
        requestMap.put("github",github);


        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");
        assertEquals(response.statusCode(),200);

        String token = response.path("token");

        Map<String ,Object> profileBody = new HashMap<>();
        profileBody.put("skills","Java,API,CSS");
        profileBody.put("status","SDET");
        profileBody.put("company",company);
        profileBody.put("website","www.amazon.com");
        profileBody.put("bio","SDET");
        profileBody.put("githubUserName","gitJR");
        profileBody.put("youtube","Youtube JR");
        profileBody.put("twitter","JR twit");
        profileBody.put("facebook","JR FaceBook");
        profileBody.put("instagram","JR Ins");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("api/profile");

        assertEquals(response.statusCode(),200);

        //verify body

        int id = response.path("user.id");

        response = given().accept(ContentType.JSON)
                .and().queryParam("id",id)
                .when().get("api/profile/userQuery");

        assertEquals(response.statusCode(),200);

        //verify with path
        assertEquals(response.path("name"),name);
        assertEquals(response.path("company"),company);


        //verify using hamcrest matcher
        given().accept(ContentType.JSON)
                .and()
                .queryParam("id",id)
                .when().get("api/profile/userQuery")
                .then().assertThat()
                .body("email",equalTo(email),
                        "name",equalTo(name),
                        "company",equalTo(company)).log().all();


    }
    // register new user
    //save new user pfofile
    //verify user informartion using JSONPATH and Hamcrest Matcher
}
