import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class DeleteTest {
    //testing delete with ID

    @BeforeTest
    public void setData() throws IOException {
        CommonClass.deleteAll();
        CommonClass.postAll();
    }


    public String sendDeleteRequest(String Url) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty(
                "Content-Type", "application/x-www-form-urlencoded" );
        con.setRequestMethod("DELETE");
        con.setDoOutput(true);
        InputStream in = con.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = reader.readLine())!= null){
            sb.append(str);
        }
        return sb.toString();
    }


    @Test(priority = 1)
    public void testingDeleteWithID() throws IOException, ParseException, JSONException {
        JSONParser parser=new JSONParser();
        String ID="438745745094";
        String response =sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID);
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
        String response =sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
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
        String response =sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
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
        String response =sendDeleteRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID);
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
