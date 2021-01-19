import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CommonClass {

    public static void deleteAll() throws IOException {
       Requests.sendDeleteRequest(URLs.baseURL);

    }


    public static void postAll() throws IOException, ParseException {

      Requests.sendPostRequest(URLs.baseURL,URLs.addManyFile);

    }


    public static String getAll() throws IOException {
      String response= Requests.sendGetRequest(URLs.baseURL);
      return response;
    }






}
