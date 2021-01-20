import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.*;

import java.io.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class GETTest {
    //getting the original Data
    String originalData;
    String dataInBoundaries;
    @BeforeClass
    public void readData() {
        FileReader reader;
        try {
            reader = new FileReader(URLs.GETAllFile);
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            originalData = data.toString();
            reader = new FileReader(URLs.GETWithBoundariesFile);
            JSONParser parserBoundaries = new JSONParser();
            JSONObject dataBoundaries = (JSONObject) parserBoundaries.parse(reader);
            dataInBoundaries = dataBoundaries.toString();

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


    @BeforeMethod
    @AfterMethod
    public void setData() throws IOException, ParseException {
        CommonClass.deleteAll();
        CommonClass.postAll();
    }

    @Test
    public void test1() throws IOException, JSONException {
       String response = HandleRestWS.sendGetRequest(URLs.baseURL);
        JSONAssert.assertEquals(response.toString(), originalData, false);

    }

    @Test
    public void TestGETWithNumbersInrangeOfSalaries() throws IOException, JSONException {
        int num1=5000;
        int num2=10000;
       String response= HandleRestWS.sendGetRequest(URLs.baseURL+num1+"/"+num2);
        JSONAssert.assertEquals(response.toString(), dataInBoundaries, false);

    }
    @Test(dataProvider ="data-provider" )
    public void TestGETWithNumbersOutrangeOfSalariesAbove(int min,int max,int expectedResult) throws IOException, JSONException {

        String response= HandleRestWS.sendGetRequest(URLs.baseURL+min+"/"+max);
        org.json.JSONObject json=new org.json.JSONObject(response);
        assertEquals(json.getInt("employeesCount"),expectedResult);
    }




    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { 100,200,0 }, { 90000,100000,0 },{5000,10000,2} };
    }


}