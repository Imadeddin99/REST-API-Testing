import org.json.simple.parser.ParseException;

import java.io.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CommonClass {

    public static void deleteAll() throws IOException {
       HandleRestWS.sendDeleteRequest(URLs.baseURL);

    }


    public static void postAll() throws IOException, ParseException {

      HandleRestWS.sendPostRequest(URLs.baseURL,URLs.addManyFile);

    }


    public static String getAll() throws IOException {
      String response= HandleRestWS.sendGetRequest(URLs.baseURL);
      return response;
    }






}
