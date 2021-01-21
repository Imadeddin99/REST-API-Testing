package TestClasses;

import Links.URLs;
import Utils.JSONUtils;
import enums.ResponseFields;
import enums.ResponseStatus;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;
import Core.CommonClass;
import requestHandling.HandleRestWS;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class DeleteTest extends CommonClass {
    //testing delete with ID



    @Test(dataProvider = "data-provider")
    public void deleteTest(String ID,String status,int employeeCount) throws IOException, ParseException, JSONException {
        String response = HandleRestWS.sendDeleteRequest(URLs.baseURL+ID);
        System.out.println(response);
        JSONObject resultData = (JSONObject) JSONUtils.convertStringToJSON(response);

        String expectedResult= (String) resultData.get(ResponseFields.STATUS.toString().toLowerCase());
        assertEquals(expectedResult,status);
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt(ResponseFields.EMPLOYEES_COUNT.toString()),employeeCount);
        CommonClass.deleteAll();
        CommonClass.postAll();

    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                { "438745745094", ResponseStatus.SUCCESS.toString(),1 },
                { "", ResponseStatus.SUCCESS.toString(),0 },
                { "438745745094", ResponseStatus.SUCCESS.toString(),1},
                {"438", ResponseStatus.ERROR.toString(),2}
        };
    }






}
