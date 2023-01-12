package apiTest.day6;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GsonTest {

    @Test
    public void jsonToMap() {


        Gson gson= new Gson();

        String myJsonBody ="{\n" +
                "    \"id\": 528,\n" +
                "    \"email\": \"eurotech@gmail.com\",\n" +
                "    \"name\": \"Teacher\",\n" +
                "    \"company\": \"Eurotech Study\",\n" +
                "    \"status\": \"Instructor\",\n" +
                "    \"profileId\": 276\n" +
                "}";

        System.out.println("myJsonBody = " + myJsonBody);

        //gson converting to map
        Map<String,Object>dataMap= gson.fromJson(myJsonBody, Map.class);
        System.out.println("dataMap = " + dataMap);

        //gson converting to object class
        EurotechUser oneUser = gson.fromJson(myJsonBody,EurotechUser.class);
     //   System.out.println("oneUser.getName() = " + oneUser.getName());

        //Serialization/////
        //Java collection or Pojo to JSON
        EurotechUser eurotechUser = new EurotechUser(11,"tomHanks@gmail.com","Tom","Hollywood","Retired",71.0);
        String jsonUser = gson.toJson(eurotechUser);
        System.out.println("Java To JSON = " + jsonUser);





    }
}
