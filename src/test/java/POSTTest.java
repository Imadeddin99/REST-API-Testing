import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;

import java.io.*;
import java.util.Locale;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class POSTTest {

    @BeforeMethod
    public void setData() throws IOException, ParseException {
      CommonClass.clearChanges();
    }


    @Test(dataProvider = "data-provider")
    public void addManyEmployeesRepeatedIDTest
            (String inputFilePath,String status,int employeeCount,int maxSalary,int minSalary,int totalSalaries)
            throws IOException, ParseException, JSONException {
        String response= HandleRestWS.sendPostRequest(URLs.baseURL, inputFilePath);
         JSONParser parser=new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get(ResponseFields.STATUS.toString().toLowerCase());
            assertEquals(expectedResult,status);
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt(ResponseFields.EMPLOYEES_COUNT.toString()),employeeCount);
            assertEquals(json.getInt(ResponseFields.MIN_SALARY.toString()),minSalary);
            assertEquals(json.getInt(ResponseFields.MAX_SALARY.toString()),maxSalary);
            assertEquals(json.getInt(ResponseFields.TOTAL_SALARIES.toString()),totalSalaries);

        }



    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                {FilesPaths.postValidFile,ResponseStatus.SUCCESS.toString(),3,35000,5000,50000},
                {FilesPaths.emptyIDFile,ResponseStatus.ERROR.toString(),2,10000,5000,15000 },
                {FilesPaths.repeatedIDFile,ResponseStatus.ERROR.toString(),2,10000,5000,15000},
                {FilesPaths.invalidIDFile,ResponseStatus.ERROR.toString(),2,10000,5000,15000},
                {FilesPaths.invalidNameFile,ResponseStatus.ERROR.toString(),2,10000,5000,15000},
                {FilesPaths.addMoreThan3File,ResponseStatus.SUCCESS.toString(),6,50000,5000,90000},
                {FilesPaths.addManyWithSameID,ResponseStatus.ERROR.toString(),3,10000,100,15100}

        };
    }





}
