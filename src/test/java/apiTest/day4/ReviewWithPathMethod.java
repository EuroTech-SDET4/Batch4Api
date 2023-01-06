package apiTest.day4;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class ReviewWithPathMethod {

    String devExUrl="http://92.205.106.232";

    @Test
    public void getAllProfiles() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(devExUrl + "/api/profile");

    //    response.prettyPrint();


        int firstId=response.path("id[2]");
        System.out.println("firstId = " + firstId);

        int lastId = response.path("id[-2]");
        System.out.println("lastId = " + lastId);

        //print second company (Eurotech)
        String secondCompany= response.path("company[1]");
        System.out.println("secondCompany = " + secondCompany);

        System.out.println("*********************************************");

        //get 1 skill's skills as list
        List<String> firstSkill=response.path("skills[0]");
        for (String skill : firstSkill) {

            System.out.println(skill);
        }
   //     System.out.println("firstSkill.size() = " + firstSkill.size());

        //get first skill's first skill
        Object firstSkillsSecondSkill = response.path("skills[0][1]");
        System.out.println("path = " + firstSkillsSecondSkill);

        Map<String,Object> firstUserMaps= response.path("user[0]");
        System.out.println("firstUserMaps = " + firstUserMaps);

        for (Object user : firstUserMaps.keySet()) {
          //  System.out.println("user = " + user);
            System.out.println(user+" : "+firstUserMaps.get(user));
        }

        Object firstUserId=response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);

        List<String> userIDS= response.path("user.id");
        //how many user ID's in devEx API
        System.out.println("userIDS.size() = " + userIDS.size());

        Object secondUserID= response.path("user.id[1]");
        System.out.println("secondUserID = " + secondUserID);


        List<Integer> allUserID= response.path("user.id");
        System.out.println("allUserID.size() = " + allUserID.size());
        System.out.println("allUserID = " + allUserID);

        List<Integer> allID= response.path("id");
        for (Integer id : allID) {
            System.out.println("id = " + id);
        }
    }
}
