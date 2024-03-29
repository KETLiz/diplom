package ru.gb.springbootlesson3.documentation;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ReadFromApiDocs r = new ReadFromApiDocs();
    //    r.URLReader(new URL("http://localhost:8080/v3/api-docs"));
        String res = r.URLReader(new URL("http://localhost:8080/v3/api-docs"));
        r.writeToFile(res);
        r.printJsonString(res, "   ");
    }
}
