package requestHandling;

import Links.FilesPaths;
import Links.URLs;
import org.json.simple.parser.ParseException;

import java.io.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Author: Imadeddin Salama
 * description: This class is used for Common methods between Test Classes such as: clearing Data,
 *              adding the original Test Data,get the data from the API and so on.
 *
 */
public class CommonClass {


    /**
     * Author: Imadeddin Salama
     * @throws IOException for connection lost.
     * description: This function is used to delete All the data in the REST API through sending DELETE request to the API.
     * This is void function ;returns nothing.
     */

    public static void deleteAll() throws IOException {
       HandleRestWS.sendDeleteRequest(URLs.baseURL);

    }

    /**
     * Author: Imadeddin Salama
     * @throws IOException for file not found and for connection lost.
     * @throws ParseException for parsing the json file.
     * description: This function is used to post the original data in the REST API through sending POST request to the API.
     * This is void function ;returns nothing.
     *
     */

    public static void postAll() throws IOException, ParseException {

      HandleRestWS.sendPostRequest(URLs.baseURL, FilesPaths.addManyFile);

    }

    /**
     * Author: Imadeddin Salama
     * @return String which contains the JSON object parsed as a String of the data in the System.
     * @throws IOException
     * description: This function is used to get all the data in the REST API through sending GET request to the API.
     */
    public static String getAll() throws IOException {
      String response= HandleRestWS.sendGetRequest(URLs.baseURL);
      return response;
    }

    /**
     * Author: Imadeddin Salama.
     * @throws IOException for file not found and for connection lost.
     * @throws ParseException for parsing the json file.
     * description: This function is used to clear the Data and insert the original Test Data again.
     * This is void function ;returns nothing.
     */
    public static void clearChanges() throws IOException, ParseException {
        deleteAll();
        postAll();
}




}
