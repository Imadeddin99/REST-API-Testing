package Tests;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.ResponseStatus;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import requestHandling.CommonClass;
import requestHandling.HandleRestWS;

import java.io.*;

import static org.testng.Assert.assertEquals;

public class PUTTest {



    @BeforeMethod
    public void setData() throws IOException, ParseException {
        CommonClass.clearChanges();
    }

    @Test(dataProvider = "data-provider")
    public void updateNameByUpdatingEmployee(String ID,String fileInputName, String fileResultName,String status) throws IOException, ParseException, JSONException {
        String response= HandleRestWS.sendPutRequest(URLs.baseURL+ID, fileInputName,status);
        String expectedData = JSONUtils.readJSONObjectFromFile(fileResultName);
        JSONAssert.assertEquals(expectedData, CommonClass.getAll(),false);
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                { "438768422146", FilesPaths.updateWithMaxSalaryFile, FilesPaths.updateWithMaxSalaryResultFile, ResponseStatus.SUCCESS.toString() },
                { "438768422146", FilesPaths.updateWithMinSalaryFile, FilesPaths.updateWithMinSalaryResultFile, ResponseStatus.SUCCESS.toString() },
                {"438745745094", FilesPaths.updateValidData, FilesPaths.updateValidDataResult, ResponseStatus.SUCCESS.toString()}
        };
    }



}
