import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CommonClass {

    public static void deleteAll() throws IOException {
        JSONParser parser=new JSONParser();
        String ID="438745745094";
        URL url = new URL("http://192.168.200.91:8080/demo-server/employee-module/Imad/");
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
        System.out.println(sb.toString());
    }


    public static void postAll() throws IOException{
        FileReader reader;
        try {
            reader = new FileReader("Data/PostData/post2.json");
            JSONParser parser = new JSONParser();

            JSONArray data = (JSONArray) parser.parse(reader);
            String originalData = data.toString();


            URL url = new URL("http://192.168.200.91:8080/demo-server/employee-module/Imad");
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
            JSONObject resultData = (JSONObject) parser.parse(sb.toString());
            String expectedResult= (String) resultData.get("status");

        }
        catch(Exception e){
            e.printStackTrace();

        }



    }


    public static String getAll() throws IOException {
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
        return sb.toString();
    }






}
