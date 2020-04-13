package main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class main {
    
    static String baseUrl = "https://api.taiga.io/api/v1/";
    static JsonDataGetter data = new JsonDataGetter();
    public static long projectId;



    public static void main(String[] args) throws ParseException {
        // TODO Auto-generated method stub
        String projectName = "amehlhase316-kaiserschmarn";
        String slugUrl = "https://api.taiga.io/api/v1/projects/by_slug?slug=";
        String projectSlugUrl = slugUrl + projectName;
        
        GetProjectId(projectSlugUrl);

        StatsGetter();
        //TasksGetter();
        //UserStoriesGetter();
        
    }
    
    public static void GetProjectId(String slug) throws ParseException {
        String slugData = data.jsonData(slug);
        //Parse the JSON data present in the string format
        JSONParser parse = new JSONParser();
        //Type caste the parsed json data in json object
        JSONObject jobj = (JSONObject)parse.parse(slugData);
        //Store the JSON object in JSON array as objects (For level 1 array element i.e Results)
        //JSONArray jsonarr = (JSONArray) jobj.get(“id”);
        projectId = (long) jobj.get("id");
        System.out.println("Project ID: " + projectId);
    }
    
    public static void StatsGetter() {
        String stats = "projects/" + projectId + "/stats";
        String statsUrl = baseUrl + stats;
        data.jsonData(statsUrl);
        
    }
    
    public static void TasksGetter() {
        String tasks = "tasks?project=" + projectId;
        String tasksUrl = baseUrl + tasks;
        data.jsonData(tasksUrl);
        
    }
    
    public static void UserStoriesGetter() {
        String userStories = "userstories?project=" + projectId;
        String usUrl = baseUrl + userStories;
        data.jsonData(usUrl);
    }

}
