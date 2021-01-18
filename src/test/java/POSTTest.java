import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class POSTTest {

    @BeforeTest
    public void setData() throws IOException {
        CommonClass.deleteAll();
        CommonClass.postAll();

    }

    public String sendPostRequest(String Url, String filePath) throws IOException, ParseException {
        FileReader reader;

        reader = new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(reader);
        String originalData = data.toString();
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setAllowUserInteraction(true);
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestMethod("POST");
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

@Test(priority = 1)
    public void validPostTest(){
    try {
        JSONParser parser = new JSONParser();
        String response=sendPostRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad","Data/PostData/Post1.json");
        System.out.println(response.toString());
        JSONObject resultData = (JSONObject) parser.parse(response.toString());
        String expectedResult= (String) resultData.get("status");
         assertEquals(expectedResult,"SUCCESS");
        org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());

        assertEquals(json.getInt("employeesCount"),3);
        CommonClass.deleteAll();
        CommonClass.postAll();
    }
    catch(Exception e){
        e.printStackTrace();
        fail();
    }



}

    @Test(priority = 2)
    public void InvalidIdPostTest(){
        try {

            String response=sendPostRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad","Data/PostData/post3.json");
            JSONParser parser = new JSONParser();

            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);
            CommonClass.deleteAll();
            CommonClass.postAll();
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test(priority = 3)
    public void RepeatedIDPostTest(){
        try {
            String response=sendPostRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad","Data/PostData/POST4.json");
            JSONParser parser = new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            String error= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);
            CommonClass.deleteAll();
            CommonClass.postAll();
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }

    @Test(priority = 10)
    public void InvalidNamePostTest(){
        try {

            String response=sendPostRequest("http://192.168.200.91:8080/demo-server/employee-module/Imad","Data/PostData/POST5.json");
            JSONParser parser = new JSONParser();
            JSONObject resultData = (JSONObject) parser.parse(response.toString());
            String expectedResult= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            String error= (String) resultData.get("status");
            assertEquals(expectedResult,"ERROR");
            org.json.JSONObject json=new org.json.JSONObject(CommonClass.getAll());
            assertEquals(json.getInt("employeesCount"),2);
            CommonClass.deleteAll();
            CommonClass.postAll();
        }
        catch(Exception e){
            e.printStackTrace();
            fail();
        }
    }







}
