import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests {

    public static HttpURLConnection sendTheRequest(String Url,String method) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-Type", "application/json" );

        con.setAllowUserInteraction(true);
        con.setDoOutput(true);
        return con;
    }
    public static void readJSONArrayAndSend(String filePath,HttpURLConnection con) throws IOException, ParseException {
        con.setDoInput(true);

        FileReader reader= new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONArray data = (JSONArray) parser.parse(reader);
        String originalData = data.toString();

        BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
        out.write(originalData.getBytes());
        out.close();

    }
    public static void readJSONObjectAndSend(String filePath,HttpURLConnection con) throws IOException, ParseException {
        con.setDoInput(true);

        FileReader reader= new FileReader(filePath);
        JSONParser parser = new JSONParser();
        JSONObject data = (JSONObject) parser.parse(reader);
        String originalData = data.toString();

        BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
        out.write(originalData.getBytes());
        out.close();

    }

    public static String readResponse(HttpURLConnection con) throws IOException {
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





    public static String sendGetRequest(String Url) throws IOException {
        HttpURLConnection con=sendTheRequest(Url,"GET");
        String response=readResponse(con);
        return response;
    }



    public static String sendPutRequest(String Url, String filePath) throws IOException, ParseException {
        HttpURLConnection con=sendTheRequest(Url,"PUT");
        readJSONObjectAndSend(filePath,con);
        String response=readResponse(con);
        return response;
    }

    public static String sendPostRequest(String Url, String filePath) throws IOException, ParseException {
        HttpURLConnection con=sendTheRequest(Url,"POST");
        readJSONArrayAndSend(filePath,con);
        String response=readResponse(con);
        return response;
    }

    public static String sendDeleteRequest(String Url) throws IOException {
        HttpURLConnection con=sendTheRequest(Url,"DELETE");
        String response=readResponse(con);
        return response;
    }




}
