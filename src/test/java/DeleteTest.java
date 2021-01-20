import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class DeleteTest {
    //testing delete with ID

    @BeforeMethod
    @AfterMethod
    public void setData() throws IOException, ParseException {
        CommonClass.deleteAll();
        CommonClass.postAll();
    }




    @Test(priority = 1)
    public void testingDeleteWithID() throws IOException, ParseException, JSONException {
        JSONParser parser=new JSONParser();
        String ID="438745745094";
        String response = HandleRestWS.sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID);
        System.out.println(response);
        JSONObject resultData = (JSONObject) parser.parse(response);
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),1);
        CommonClass.deleteAll();
        CommonClass.postAll();




    }

    @Test(priority = 2)
    public void testingDeleteAll() throws IOException, ParseException, JSONException {
        JSONParser parser=new JSONParser();
        String ID="438745745094";
        String response = HandleRestWS.sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
        System.out.println(response);
        JSONObject resultData = (JSONObject) parser.parse(response);
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),0);
        CommonClass.deleteAll();
        CommonClass.postAll();
    }

    @Test(priority = 3)
    public void testingDeleteAllEmpty() throws IOException, ParseException, JSONException {
        JSONParser parser=new JSONParser();
        String ID="438745745094";
        String response = HandleRestWS.sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
        System.out.println(response);
        JSONObject resultData = (JSONObject) parser.parse(response);
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),0);
       CommonClass.deleteAll();
       CommonClass.postAll();
    }


    @Test(priority = 6)
    public void testingDeleteWithdoesntExistID() throws IOException, ParseException, JSONException {
        JSONParser parser=new JSONParser();
        String ID="438";
        String response = HandleRestWS.sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID);
        System.out.println(response);
        JSONObject resultData = (JSONObject) parser.parse(response);
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"ERROR");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),2);
        CommonClass.deleteAll();
        CommonClass.postAll();




    }


}
