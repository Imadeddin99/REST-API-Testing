import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class PUTTest {

    public String sendPutRequest(String Url, String filePath) throws IOException, ParseException {
        FileReader reader;

        reader = new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(reader);
        String originalData = data.toString();
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setAllowUserInteraction(true);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty(
                "Content-Type", "application/json" );

        BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
        out.write(originalData.getBytes());
        out.close();


        InputStream in = con.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str;
        while((str = bufferedReader.readLine())!= null){
            sb.append(str);
        }
        con.disconnect();
        bufferedReader.close();
        System.out.println(sb.toString());
        return sb.toString();
    }

    @BeforeMethod
    @AfterMethod
    public void setData() throws IOException {
        CommonClass.deleteAll();
        CommonClass.postAll();

    }


@Test
    public void updateOnMaxSalaryByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438768422146";
    String response=sendPutRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID,"Data/PutData/put1.json");
    JSONParser parser = new JSONParser();
    JSONObject resultData = (JSONObject) parser.parse(response.toString());
    String expectedResult= (String) resultData.get("status");
    assertEquals(expectedResult,"SUCCESS");

   FileReader reader = new FileReader("Data/PutData/put1Result.json");
    JSONParser parserBoundaries = new JSONParser();
    JSONObject json = (JSONObject) parserBoundaries.parse(reader);
    String expectedData = json.toString();System.out.println(expectedData);
    JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




}


    @Test
    public void updateOnMinSalaryByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438768422146";
        String response=sendPutRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID,"Data/PutData/put2.json");
        JSONParser parser = new JSONParser();
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");

        FileReader reader = new FileReader("Data/PutData/put2Result.json");
        JSONParser parserBoundaries = new JSONParser();
        JSONObject json = (JSONObject) parserBoundaries.parse(reader);
        String expectedData = json.toString();System.out.println(expectedData);
        JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




    }

    @Test
    public void updateNameByUpdatingEmployee() throws IOException, ParseException, JSONException {
        String ID="438745745094";
        String response=sendPutRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad/"+ID,"Data/PutData/put3.json");
        JSONParser parser = new JSONParser();
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,"SUCCESS");

        FileReader reader = new FileReader("Data/PutData/put3Result.json");
        JSONParser parserBoundaries = new JSONParser();
        JSONObject json = (JSONObject) parserBoundaries.parse(reader);
        String expectedData = json.toString();System.out.println(expectedData);
        JSONAssert.assertEquals(expectedData,CommonClass.getAll(),false);




    }



}
