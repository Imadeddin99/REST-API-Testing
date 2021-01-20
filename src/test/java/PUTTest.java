import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.assertEquals;

public class PUTTest {



    @BeforeMethod
    @AfterMethod
    public void setData() throws IOException, ParseException {
        CommonClass.deleteAll();
        CommonClass.postAll();

    }


@Test
    public void updateOnMaxSalaryByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438768422146";
    String response= HandleRestWS.sendPutRequest(URLs.baseURL+ID,URLs.updateWithMaxSalaryFile);
    JSONParser parser = new JSONParser();
    JSONObject resultData = (JSONObject) parser.parse(response.toString());
    String expectedResult= (String) resultData.get("status");
    assertEquals(expectedResult,"SUCCESS");

   FileReader reader = new FileReader(URLs.updateWithMaxSalaryResultFile);
    JSONParser parserBoundaries = new JSONParser();
    JSONObject json = (JSONObject) parserBoundaries.parse(reader);
    String expectedData = json.toString();System.out.println(expectedData);
    JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




}


    @Test
    public void updateOnMinSalaryByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438768422146";
        String response= HandleRestWS.sendPutRequest(URLs.baseURL+ID,URLs.updateWithMinSalaryFile);
        JSONParser parser = new JSONParser();
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");

        FileReader reader = new FileReader(URLs.updateWithMinSalaryResultFile);
        JSONParser parserBoundaries = new JSONParser();
        JSONObject json = (JSONObject) parserBoundaries.parse(reader);
        String expectedData = json.toString();System.out.println(expectedData);
        JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




    }

    @Test
    public void updateNameByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438745745094";
        String response= HandleRestWS.sendPutRequest(URLs.baseURL+ID, URLs.updateValidData);
        JSONParser parser = new JSONParser();
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");

        FileReader reader = new FileReader(URLs.updateValidDataResult);
        JSONParser parserBoundaries = new JSONParser();
        JSONObject json = (JSONObject) parserBoundaries.parse(reader);
        String expectedData = json.toString();System.out.println(expectedData);
        JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




    }



}
