package com.example.demo;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class htm2json {
    public String TableToJson(String source) throws JSONException {
        Document doc = Jsoup.parse(source);
        JSONObject jsonParentObject = new JSONObject();
        //JSONArray list = new JSONArray();
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                JSONObject jsonObject = new JSONObject();
                Elements tds = row.select("td");
                String Name = tds.get(0).text();
                String Group = tds.get(1).text();
                String Code = tds.get(2).text();
                String Lesson = tds.get(3).text();
                String Day1 = tds.get(4).text();
                String Day2 = tds.get(5).text();
                String Day3= tds.get(6).text();
                jsonObject.put("Group", Group);
                jsonObject.put("Code", Code);
                jsonObject.put("Lesson", Lesson);
                jsonObject.put("Day1", Day1);
                jsonObject.put("Day2", Day2);
                jsonObject.put("Day3", Day3);
                jsonParentObject.put(Name,jsonObject);
            }
        }
        return jsonParentObject.toString();
    }

    private static File getLogFileFromMicrobit() {
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();

        // returns pathnames for files and directory
        paths = File.listRoots();

        // for each pathname in pathname array
        for(File path:paths)
        {
            String description = fsv.getSystemTypeDescription(path);
            if (description.equals("USB Drive")) {
                File[] files = path.listFiles();
                assert files != null;
                for (File file : files) {
                    if (file.getName().equals("MY_DATA.HTM")) {
                        return file;
                    }
                }
            }

        }
        return null;
    }

}
