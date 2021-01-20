import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class POSTTest {

    @BeforeMethod
    public void setData() throws IOException, ParseException {
        CommonClass.deleteAll();
        CommonClass.postAll();

    }
    @AfterMethod
    public void setDataAfterTest() throws IOException, ParseException {
        CommonClass.deleteAll();
        CommonClass.postAll();

    }


@Test(priority = 1)
    public void validPostTest(){
    try {
        JSONParser parser = new JSONParser();
        String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.postValidFile);
        System.out.println(response.toString());
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
         assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),3);

    }
    catch(Exception e){
        e.printStackTrace();
        fail();
    }



}

    @Test(priority = 2)
    public void InvalidIdPostTest(){
        try {
            String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.emptyIDFile);
            JSONParser parser = new JSONParser();

            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);

        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test(priority = 3)
    public void RepeatedIDPostTest(){
        try {
            String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.repeatedIDFile);
            JSONParser parser = new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            String error= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);

        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }
    @Test(priority = 4)
    public void InvalidIDPostTest(){
        try {
            String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.invalidIDFile);
            JSONParser parser = new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            String error= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);

        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test(priority = 10)
    public void InvalidNamePostTest(){
        try {

            String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.invalidNameFile);
            JSONParser parser = new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            String error= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);

        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }

    }

@Test(priority = 1 )
    public void addManyEmployeesTest() throws IOException, ParseException, JSONException {

 String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.addMoreThan3File);
        JSONParser parser=new JSONParser();
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
        assertEquals(json.getInt("employeesCount"),6);
        assertEquals(json.getInt("maxSalary"),50000);


    }




    @Test(priority = 7 )
    public void addManyEmployeesRepeatedIDTest() throws IOException, ParseException, JSONException {

        String response= HandleRestWS.sendPostRequest(URLs.baseURL,URLs.addManyWithSameID);
         JSONParser parser=new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());

            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),3);
            assertEquals(json.getInt("minSalary"),100);


        }









}
