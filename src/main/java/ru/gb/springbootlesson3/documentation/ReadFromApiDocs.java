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

    //URL url = new URL("http://localhost:8080/v3/api-docs");

    public ReadFromApiDocs() throws MalformedURLException {
    }

    public void URLReader(URL url) throws IOException, ParseException {

        String line;
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(sb.toString());
        String result = sb.toString();
        //System.out.println(result);
        StringBuilder n = new StringBuilder();
        char c = ',';
        int i = 0;
        int startIndex = 0;
        String oneString = null;
        while(i < result.length()) {
            if(result.charAt(i) == c) {
                int endIndex = i+1;
                oneString = result.substring(startIndex, endIndex);
                n.append(oneString);
                n.append("\n");
                //i++;
                startIndex = endIndex;
            }
            i++;
            if(i == result.length()-1) {
                oneString = result.substring(startIndex);
                n.append(oneString);
            }
        }
        System.out.println(n.toString());
//        JSONObject jsonObj = (JSONObject) JSONValue.parseWithException(sb.toString());
//        System.out.println(jsonObj);

//        ObjectMapper mapper = new ObjectMapper();
//        String prettyJsonString = mapper.writerWithDefaultPrettyPrinter()
//                .writeValueAsString(sb.toString());
//        System.out.println(prettyJsonString);

//        StringBuilder sb = new StringBuilder();
//        String line;
//
//        InputStream in = url.openStream();
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            while ((line = reader.readLine()) != null) {
//                sb.append(line).append(System.lineSeparator());
//            }
//        } finally {
//            in.close();
//        }

        //System.out.println(sb.toString());
        String outputFileName = "C:/Users/Саша/IdeaProjects/spring-boot-lesson-3/src/" +
                "main/java/ru/gb/springbootlesson3/documentation/file.txt";

        try (BufferedWriter writter = new BufferedWriter(new FileWriter(outputFileName))) {
            writter.write(n.toString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
