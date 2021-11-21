/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.zip.GZIPInputStream;
import org.json.*;

/** 
 * Class that handles the API requests
 *
 * @author bencusb
 */
public final class apiCalling {
    /**
     * Opens an HTTP client
     */
    private static final HttpClient httpClient = HttpClient.newBuilder()
         .version(HttpClient.Version.HTTP_1_1)
         .connectTimeout(Duration.ofSeconds(30))
         .build();
    /**
    * JsonObject m_obj
    */
    private JSONObject m_obj;
    /**
    * JsonObject m_dates
    */
    private JSONObject m_dates;
    /**
    * m_country String 
    */
    private String m_country;
     
    /**
     * Returns the next day
     * 
     * @param curDate current date
     * @return The next date
     * @throws ParseException ParseException
     */
    public String getNextDate(String  curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime()); 
      }
    
    /**
     * API calling with parameters
     * It gets the data from a desired start date up until a desired end day from a desired region
     * 
     * @param start_date The first date we want for our sample pool
     * @param end_date The last day we want for our sample pool
     * @param country country
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException
     * @throws java.net.URISyntaxException java.net.URISyntaxException
     */
    public apiCalling(String start_date,String end_date, String country) throws IOException, InterruptedException, ParseException, URISyntaxException
    {
        fetch(start_date,end_date,country);
        m_country = country;
    }
    /**
     * Calls the API
     * 
     * @param param parameter
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException 
     */
    public apiCalling(String param) throws IOException, InterruptedException, ParseException
    {
        if("fetchCountryList".equals(param))
            fetchCountryList();
    }
    
    /**
     * Calls the API
     * 
     * @param date date
     * @param country country
     * @param region region
     * @param days days
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException
     * @throws URISyntaxException  URISyntaxException 
     */
    public apiCalling(String date, String country, String region, int days) throws IOException, InterruptedException, ParseException, URISyntaxException
    {
            fetchRegion(date,country,region);
    }
    
    /**
     * Sends the HTTP GET request to the API
     *
     * @param start_date The first date we want for our sample pool
     * @param end_date The last day we want for our sample pool
     * @param country country
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException
     * @throws java.net.URISyntaxException java.net.URISyntaxException
     */
    public final void fetch(String start_date,String end_date, String country) throws IOException, InterruptedException, ParseException, URISyntaxException
    {
        String string = "https://api.covid19tracking.narrativa.com/api/country/"+country+"?date_from="+start_date+"&date_to="+end_date;
        
        URI uri = new URI(string.replace(" ", "%20"));
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Accept-Encoding", "compress, gzip").build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            //.thenAccept(System.out::println)
            .join();
        
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        
        InputStream gzipStream = new GZIPInputStream(response.body());
               
            BufferedReader bfr = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
            String outputString = "";
            String line;
            while ((line=bfr.readLine())!=null) {
              outputString += line;
            }
        
        m_obj = new JSONObject(outputString);
        
        //Uncomment for debuging purposes
                
                System.out.println("params: " + country + " " + start_date + " " + end_date);
                
                // print response headers
                HttpHeaders headers = response.headers();
                headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
                
                // print status code
                System.out.println("status: " + response.statusCode());
                
                // print response body
                System.out.println("body: " + outputString);
    }
    /**
     *Fetches country list
     * 
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException 
     */
    public void fetchCountryList() throws IOException, InterruptedException, ParseException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.covid19tracking.narrativa.com/api/countries")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(System.out::println)
            .join();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        m_obj = new JSONObject(response.body());
        
        //Uncomment for debuging purposes
        
        //print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println("status: " + response.statusCode());

        // print response body
        System.out.println("body: " + response.body());
    }
    
    /**
     * Fetches Region list
     * 
     * @param country country
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException
     * @throws URISyntaxException  URISyntaxException 
     */
    public void fetchRegionList(String country) throws IOException, InterruptedException, ParseException, URISyntaxException
    {
        String string = "https://api.covid19tracking.narrativa.com/api/countries/" + country + "/regions";
        
        URI uri = new URI(string.replace(" ", "%20"));
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Accept-Encoding", "compress, gzip").build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            //.thenAccept(System.out::println)
            .join();
        
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        
        InputStream gzipStream = new GZIPInputStream(response.body());
               
            BufferedReader bfr = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
            String outputString = "";
            String line;
            while ((line=bfr.readLine())!=null) {
              outputString += line;
            }
        
        m_obj = new JSONObject(outputString);
        
        //Uncomment for debuging purposes
        
        //print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println("status: " + response.statusCode());

        // print response body
        System.out.println("body: " + outputString);
    }
    
    /**
     * Fetches Region
     * 
     * @param date date
     * @param country country
     * @param region region
     * @throws IOException IOException
     * @throws InterruptedException InterruptedException
     * @throws ParseException ParseException
     * @throws URISyntaxException URISyntaxException 
     */
    public void fetchRegion(String date, String country, String region) throws IOException, InterruptedException, ParseException, URISyntaxException
    {
        String string = "https://api.covid19tracking.narrativa.com/api/"+date+"/country/"+country+"/region/"+region;
        
        URI uri = new URI(string.replace(" ", "%20"));
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).header("Accept-Encoding", "compress, gzip").build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            //.thenAccept(System.out::println)
            .join();
        
        HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        
        InputStream gzipStream = new GZIPInputStream(response.body());
               
            BufferedReader bfr = new BufferedReader(new InputStreamReader(gzipStream, "UTF-8"));
            String outputString = "";
            String line;
            while ((line=bfr.readLine())!=null) {
              outputString += line;
            }
        
        m_obj = new JSONObject(outputString);
        
        //Uncomment for debuging purposes
        
        //print response headers
        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

        // print status code
        System.out.println("status: " + response.statusCode());

        // print response body
        System.out.println("body: " + outputString);
    }
    //Uncomment for debuging purposes
    
    /*public static void main(String args[]) throws IOException, InterruptedException, ParseException {
        
         //Case 1
         //apiCalling test1 = new apiCalling("2021-09-11","2021-10-20","Hungary");
         //System.out.println(test1.getTodayNewConfirmed());
         //System.out.println(test1.getTodayNewDeaths());
         
         //Case 2
         //apiCalling test2 = new apiCalling("fetchCountryList");
         //System.out.println(test2.getCountries());
         
         //Case 3
         apiCalling test3 = new apiCalling("fetchCountryList");
         System.out.println(test3.getCountries());
         try{
             test3.fetchRegionList("Us");
         }catch(Exception e){
             
         }
         
         System.out.println(test3.getRegions("Us"));
    }
    */
    /**
     * Returns values from regions
     * 
     * @param valName valName
     * @return the result of the data of the selected region
     */
    private TreeMap<String,Integer> getValFromCountry(String valName)
    {
        m_dates = (JSONObject)(m_obj.get("dates"));
        
        TreeMap<String,Integer> res = new TreeMap<>();
        
        m_dates.keySet().forEach(d -> {
            JSONObject date = (JSONObject)(m_dates.get(d));
            JSONObject countries = (JSONObject)(date.get("countries"));
            JSONObject country = (JSONObject)(countries.get(m_country));
            int new_confirmed = country.getInt(valName);
            res.put(d, new_confirmed);
        });
        
        
        return res;
    }
    
    /**
     * returns values from regions
     * 
     * @param valName val Name
     * @return res res
     */
    private TreeMap<String,Integer> getValFromRegion(String valName)
    {
        m_dates = (JSONObject)(m_obj.get("dates"));
        
        TreeMap<String,Integer> res = new TreeMap<>();
        
        m_dates.keySet().forEach(d -> {
            JSONObject date = (JSONObject)(m_dates.get(d));
            JSONObject countries = (JSONObject)(date.get("countries"));
            JSONObject country = (JSONObject)(countries.get(m_country));
            JSONArray regions = (JSONArray)(country.get("regions"));
            JSONObject region = (JSONObject)(regions.getJSONObject(0));
            int value = region.getInt(valName);
            res.put(d, value);
        });
        
        
        return res;
    }
    
    /**
     * returns countries
     * 
     * @return res
     */
    public List<String> getCountries()
    {
        JSONArray countries = (JSONArray)(m_obj.get("countries"));
        
        List<String> res = new ArrayList<>();
        
        for(int i = 0; i < countries.length(); i++){
            JSONObject id = (JSONObject)(countries.getJSONObject(i));
            String name = id.getString("name");
            res.add(name);
        }
        
        return res;
    }
    
     /**
      * returns regions
      * 
      * @param valName value name
      * @return res
      */
    public List<String> getRegions(String valName)
    {
        JSONArray countries = (JSONArray)(m_obj.get("countries"));
        JSONObject id = (JSONObject)(countries.getJSONObject(0));
        JSONArray country  = (JSONArray)(id.get(valName));
        
        List<String> res = new ArrayList<>();
        
        
        for(int i = 0; i < country.length(); i++){
            JSONObject regiaonId = country.getJSONObject(i);
            String name = regiaonId.getString("name");
            res.add(name);
        }
        
        return res;
    }
    
    /**
     * Returns today's new confirmed cases
     * 
     * @return today's new confirmed cases
     */
    public TreeMap<String,Integer> getTodayNewConfirmed()
    {
        return getValFromCountry("today_new_confirmed");
    }
    
    /**
     * Returns today's new death numbers
     * 
     * @return today's new death cases
     */
    public TreeMap<String,Integer> getTodayNewDeaths()
    {
        return getValFromCountry("today_new_deaths");
    }

    /**
     * Gets the confirmed from the region
     * 
     * @return the value from region
     */
    public TreeMap<String,Integer> getRegionNewConfirmed()
    {
        return getValFromRegion("today_new_confirmed");
    }
    
    /**
     * Returns today's new death numbers
     * 
     * @return today's new death cases
     */
    public TreeMap<String,Integer> getRegionNewDeaths()
    {
        return getValFromRegion("today_new_deaths");
    }
}
