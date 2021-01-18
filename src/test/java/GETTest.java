import org.json.JSONException;
import org.json.simple.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.fail;

public class GETTest {
    //getting the original Data
    String originalData;
    String dataInBoundaries;
    @BeforeClass
    public void readData() {
        FileReader reader;
        try {
            reader = new FileReader("Data/GetData/GETData.json");
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(reader);
            originalData = data.toString();
            reader = new FileReader("Data/GetData/GETData2.json");
            JSONParser parserBoundaries = new JSONParser();
            JSONObject dataBoundaries = (JSONObject) parserBoundaries.parse(reader);
            dataInBoundaries = dataBoundaries.toString();

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test1() throws IOException, JSONException {
        URL url = new URL("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
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
        JSONAssert.assertEquals(sb.toString(), originalData, false);

    }

    @Test
    public void test2() throws IOException, JSONException {
        URL url = new URL("http://192.168.200.91:8080/demo-server/employee-module/Imad/5000/10000");
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
        JSONAssert.assertEquals(sb.toString(), dataInBoundaries, false);

    }




}