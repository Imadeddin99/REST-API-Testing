import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;

/**
 * Author: Imadeddin Salama
 * Description: This class is used to handle HTTP requests mentioned in the requirements : PUT DELETE GET POST
 *              through the functions in this class.
 */
public class HandleRestWS {





    /**
     * Author: Imadeddin Salama.
     * @param Url: specifies the URL we are sending the request to.
     * @param method: specifies the HTTP request method we are using to send the request
     * @return: returns the connection we create to send the request to be used to send any data or get the response later.
     * @throws IOException: for connection lost.
     * Description: the function is used to send the whole request by the method specified in the params
     */
    public static HttpURLConnection sendTheRequest(String Url, HTTPMethod method,HTTPRequestsContentTypes contentType) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method.toString());
        con.setRequestProperty(HTTPConstants.REQUEST_PROPERTY_CONTENT_TYPE, contentType.toString() );

        con.setAllowUserInteraction(true);
        con.setDoOutput(true);
        return con;
    }







    /**
     * Author: Imadeddin Salama.
     * @param con: specifies the connection that was used to send the request , which is going to be used to send data.
     * @param data: specifies the data we want to send through the HTTP connection.
     * @throws IOException for connection not found.
     * Description: the function is used to send the data on the previous opened connection.
     */
    public static void sendData(HttpURLConnection con,String data) throws IOException {
        con.setDoInput(true);
        BufferedOutputStream out = new BufferedOutputStream(con.getOutputStream());
        out.write(data.getBytes());
        out.close();
    }






    /**
     * Author: Imadeddin Salama.
     * @param filePath: specifies the path of the file we want to read and Send through the request.
     * @param con: specifies the connection that was used to send the request , which is going to be used to send data.
     * @throws IOException: for file not found or connection lost.
     * @throws ParseException: for parsing errors.
     * Description: the function is used to read the JSON Array and send the data.
     */
    public static void readJSONArrayAndSend(String filePath,HttpURLConnection con) throws IOException, ParseException {
        String originalData=JSONUtils.readJSONArrayFromFile(filePath);
       sendData(con,originalData);

    }







    /**
     * Author: Imadeddin Salama.
     * @param filePath: specifies the path of the file we want to read and Send through the request.
     * @param con: specifies the connection that was used to send the request , which is going to be used get the response.
     * @throws IOException: for file not found or connection lost.
     * @throws ParseException: for parsing errors.
     * Description: the function is used to read the JSON Object and send the data.
     */
    public static void readJSONObjectAndSend(String filePath,HttpURLConnection con) throws IOException, ParseException {
        String originalData = JSONUtils.readJSONObjectFromFile(filePath);
        sendData(con,originalData);

    }








    /**
     * Author: Imadeddin Salama.
     * @param con: specifies the connection that was used to send the request , which is going to be used toget the response.
     * @return returns the response as String
     * @throws IOException: for connection lost.
     * Description: the function is used to read the response of the opened connection.
     */
    public static String readResponse(HttpURLConnection con) throws IOException {
        InputStream in = con.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(isReader);
        StringBuffer stringBuffer = new StringBuffer();
        String str;
        while((str = bufferedReader.readLine())!= null){
            stringBuffer.append(str);
        }
        con.disconnect();
        bufferedReader.close();
        System.out.println(stringBuffer.toString());
        return stringBuffer.toString();
    }


    /**
     * Author: Imadeddin Salama.
     * @param Url:Specifies the URL we are going to send the request through.
     * @return returns the response of the GET request.
     * @throws IOException for connection lost.
     * Description: the function is used to send GET HTTP Request to API with specified URL.
     */
    public static String sendGetRequest(String Url) throws IOException {
        HttpURLConnection con=sendTheRequest(Url, HTTPMethod.GET,HTTPRequestsContentTypes.JSON);
        String response=readResponse(con);
        return response;
    }





    /**
     * Author: Imadeddin Salama.
     * @param Url:Specifies the URL we are going to send the request through.
     * @param filePath:Specifies the path of the file we are going to read and send the data through.
     * @return returns the response of the PUT request.
     * @throws IOException for connection lost.
     * @throws ParseException for parsing problems of file reading.
     * Description: the function is used to send PUT HTTP Request to API with specified URL with reading files as JSON FORMAT.
     */
    public static String sendPutRequest(String Url, String filePath,String status) throws IOException, ParseException {
        HttpURLConnection con=sendTheRequest(Url, HTTPMethod.PUT,HTTPRequestsContentTypes.JSON);
        readJSONObjectAndSend(filePath,con);
        String response=readResponse(con);
        JSONObject resultData = (JSONObject) JSONUtils.convertStringToJSON(response);
        String expectedResult= (String) resultData.get("status");
        assertEquals(expectedResult,ResponseStatus.SUCCESS.toString());
        return response;
    }


    /**
     *
     * @param Url: Specifies the URL we are going to send the request through.
     * @param filePath: Specifies the path of the file we are going to read and send the data through.
     * @return the response of the POST request.
     * @throws IOException for connection lost.
     * @throws ParseException for parsing problems of file reading.
     * Description: the function is used to send PUT HTTP Request to API with specified URL with reading files as JSON FORMAT.
     */
    public static String sendPostRequest(String Url, String filePath) throws IOException, ParseException {
        HttpURLConnection con=sendTheRequest(Url, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
        readJSONArrayAndSend(filePath,con);
        String response=readResponse(con);
        return response;
    }


    /**
     *
     * @param Url: Specifies the URL we are going to send the request through.
     * @return the response of the DELETE request.
     * @throws IOException for connection lost.
     * Description: the function is used to send DELETE HTTP Request to API with specified URL
     */
    public static String sendDeleteRequest(String Url) throws IOException {
        HttpURLConnection con=sendTheRequest(Url, HTTPMethod.DELETE,HTTPRequestsContentTypes.JSON);
        String response=readResponse(con);
        return response;
    }




}
