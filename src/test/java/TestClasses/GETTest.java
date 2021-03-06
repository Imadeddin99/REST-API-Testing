package TestClasses;

import Utils.JSONUtils;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.*;

import java.io.*;
import Links.*;
import Core.CommonClass;
import requestHandling.HandleRestWS;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class GETTest extends CommonClass{
    //getting the original Data






    @Test
    public void getTest() throws IOException, JSONException, ParseException {

       String response = HandleRestWS.sendGetRequest(URLs.baseURL);
       String originalData= JSONUtils.readJSONObjectFromFile(FilesPaths.GETAllFile);
       JSONAssert.assertEquals(response.toString(), originalData, false);

    }

    @Test(dataProvider ="data-provider" )
    public void TestGETWithNumbersOutrangeOfSalariesAbove(int min,int max,int expectedResult,String expectedJSONFilePath)
            throws IOException, JSONException, ParseException {

        String response= HandleRestWS.sendGetRequest(URLs.baseURL+min+"/"+max);
        org.json.JSONObject json=new org.json.JSONObject(response);
        assertEquals(json.getInt("employeesCount"),expectedResult);
        JSONAssert.assertEquals(JSONUtils.readJSONObjectFromFile(expectedJSONFilePath),response,false);
    }




    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] { { 100,200,0, FilesPaths.GETWithEmptyEmployeesFile },
                { 90000,100000,0, FilesPaths.GETWithEmptyEmployeesFile },
                {5000,10000,2, FilesPaths.GETAllFile} };
    }


}