import org.json.JSONException;
import org.json.simple.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

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
            reader = new FileReader("Data\\GetData\\GETData.json");
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            originalData = data.toString();
            reader = new FileReader("Data\\GetData\\GETData2.json");
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
    public void setData() throws IOException {
        CommonClass.deleteAll();
        CommonClass.postAll();
    }
    public String sendGetRequest(String Url) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        int status = con.getResponseCode();
        byte[] result = new byte[2000];
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
    @Test
    public void test1() throws IOException, JSONException {
       String response =sendGetRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
        JSONAssert.assertEquals(response.toString(), originalData, false);

    }

    @Test
    public void TestGETWithNumbersInrangeOfSalaries() throws IOException, JSONException {
        int num1=5000;
        int num2=10000;
       String response=sendGetRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+num1+"/"+num2);
        JSONAssert.assertEquals(response.toString(), dataInBoundaries, false);

    }
    @Test
    public void TestGETWithNumbersOutrangeOfSalariesAbove() throws IOException, JSONException {
        int num1=90000;
        int num2=100000;
        String response=sendGetRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+num1+"/"+num2);
        org.json.JSONObject json=new org.json.JSONObject(response);
        assertEquals(json.getInt("employeesCount"),0);
    }

    @Test
    public void TestGETWithNumbersOutrangeOfSalariesLess() throws IOException, JSONException {
        int num1=100;
        int num2=200;
        String response=sendGetRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+num1+"/"+num2);
        org.json.JSONObject json=new org.json.JSONObject(response);
        assertEquals(json.getInt("employeesCount"),0);
    }



}