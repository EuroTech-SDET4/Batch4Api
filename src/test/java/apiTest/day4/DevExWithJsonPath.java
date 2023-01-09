package apiTest.day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class DevExWithJsonPath {

    String devExUrl="http://92.205.106.232";

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", "74")
                .when().get(devExUrl + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);

        response.prettyPrint();

        System.out.println("response.path(\"email\") = " + response.path("email"));

        System.out.println("**************************************");

        JsonPath jsonPath = response.jsonPath();


        int userId = jsonPath.getInt("id");
        System.out.println("userId = " + userId);

        String emailJson=jsonPath.getString("email");
        String nameJson= jsonPath.getString("name");
        String companyJson=jsonPath.getString("company");
        String statusJson=jsonPath.getString("status");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("statusJson = " + statusJson);

        assertEquals(userId,74);
        assertEquals(nameJson,"Alice");
        assertEquals(companyJson,"TCS");
        assertEquals(statusJson,"Automation Test Engineer");


    }

    @Test
    public void task() {

        /*
        Given accept type is json
        And query param 29
        Status code 200
        Content Type application Json
        verify user information with using JsonPath
        {
    "id": 29,
    "email": "oyku@gmail.com",
    "name": "oyku",
    "company": "Microsoft",
    "status": "Student or Learning",
    "profileId": 11
}
         */


    }

    @Test
    public void test2() {

        Response response = RestAssured.get(devExUrl + "/api/profile");
      //  response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        int secondUserId=jsonPath.getInt("id[1]");
        System.out.println("secondUserId = " + secondUserId);

        //get all company name
        List<Object> allCompanies = jsonPath.getList("company");
        System.out.println(allCompanies);

        Map<String,Object> secondUserInfo =jsonPath.getMap("user[1]");
        System.out.println("secondUserInfo = " + secondUserInfo);
        System.out.println("secondUserInfo.get(\"google\") = " + secondUserInfo.get("google"));

        //get  second user's skills
        List<String> secondUsersSkills = jsonPath.getList("skills[1]");
        System.out.println("secondUsersSkills = " + secondUsersSkills);

        //get all user name which has githup is null
        List<Object> listGithub = jsonPath.getList("user.findAll{it.github==null}.name");
        System.out.println("listGithub = " + listGithub);

        List<Object> listI = jsonPath.getList("user.findAll{it.id<50}.company");
        System.out.println("listI = " + listI);


    }
}
