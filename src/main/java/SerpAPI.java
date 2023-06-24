package main.java;

import java.net.URI;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SerpAPI {
    public static String getJSONFromFile (String filename){
        String jsonText = "";

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = bufferedReader.readLine()) != null){
                jsonText += line +"\n";
            }
            bufferedReader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public static ArrayList getItems(){
        String strJson = getJSONFromFile("src\\main\\java\\serpAPI.json");
        ArrayList<ArrayList> items = new ArrayList<ArrayList>();
        try {
            JSONParser parser = new JSONParser();
            Object object = parser.parse(strJson);
            JSONObject mainJsonObject = (JSONObject) object;
            JSONArray mainArray = ((JSONArray)(mainJsonObject.get("visual_matches")));
            for (int i = 0; i<mainArray.size();i++){
                try{
                    String price = (String)((JSONObject)mainArray.get(i)).get("price");
                }catch(java.lang.ClassCastException e){
                    String price = ((JSONObject)mainArray.get(i)).get("price").toString().split("\"")[9];
                    int position = Integer.parseInt(((JSONObject)mainArray.get(i)).get("position").toString());
                    String link  = ((JSONObject)mainArray.get(i)).get("link").toString();
                    String source  = ((JSONObject)mainArray.get(i)).get("source").toString();
                    String title  = ((JSONObject)mainArray.get(i)).get("title").toString();
                    String source_icon  = ((JSONObject)mainArray.get(i)).get("source_icon").toString();
                    //String[] itemInfo = new String[]{title,price,source,link,source_icon};
                    ArrayList<String> individualItemInfo = new ArrayList<String>(Arrays.asList(new String[]{title,price,source,link,source_icon}));
                    items.add(individualItemInfo);
                    //System.out.println(items.toString());
                } 
            }
            //String searchData = (String)(((JSONObject)mainArray.get(20)).get("price"));
            System.out.println(mainArray.size());
        } catch (ParseException ex) {
            Logger.getLogger(SerpAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }
    
    public static void writeURLToFile(String url) {
        String filePath = "src\\main\\java\\url.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(url);
            System.out.println("URL successfully written to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList search(String url){
        writeURLToFile(url);
        
        String pythonScriptPath = "src\\main\\java\\serpAPI.py";
        
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);
            Process process = processBuilder.start();

            // Optionally, you can read the output or perform other operations on the process

            int exitCode = process.waitFor();
            System.out.println("Python script execution completed with exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return getItems();
    }
    
}