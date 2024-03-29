package ru.gb.springbootlesson3.documentation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class ReadFromApiDocs {

    public ReadFromApiDocs() throws MalformedURLException {
    }

    public String URLReader(URL url) throws IOException, ParseException {

        String line;
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void writeToFile(String result) {
        String outputFileName = "C:/Users/Саша/IdeaProjects/spring-boot-lesson-3/src/" +
                "main/java/ru/gb/springbootlesson3/documentation/file.txt";

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printJsonString(String result, String indent) {
        String oneLine = null;
        int startIndex = 0;
        int endIndex = 0;
        boolean wasChar = true;

        for(int i = 0; i < result.length(); i++) {
            if(i == result.length() - 1) {
                oneLine = result.substring(startIndex);
                System.out.println(oneLine);
            }
            if(result.charAt(i) == ',') {
                endIndex = i;
                oneLine = result.substring(0, endIndex+1);

                System.out.println(oneLine);
                if(!wasChar) {
                    System.out.print(indent);
                }
                result = result.substring(endIndex+1);
                i = 0;
            }
            if(result.charAt(i) == ':') {
                if(result.charAt(i+1) == '{') {
                    wasChar = false;
                    i++;
                    endIndex = i;
                    //indent = indent+indent;
                    oneLine = result.substring(0, endIndex+1);
                    System.out.println(oneLine);
                    System.out.print(indent);
                    result = result.substring(endIndex+1);
                    i = 0;
                }
            }
            if(result.charAt(i) == '}') {
                wasChar = true;
                //System.out.print(indent);
            }
        }

    }
}
