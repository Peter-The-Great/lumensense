package com.example.demo.utils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Log class which will log everything into the log displays.
public class LogUtils {
    //Get the logs and put them into a neatly made Arraylist
    public HashMap<String, String> getLogs() throws RuntimeException {
        HashMap<String, ArrayList<String>> logList = getLogsList();
        HashMap<String, String> logs = new HashMap<>();
        for (String type : logList.keySet()) {
            StringBuilder content = new StringBuilder();
            for (String log : logList.get(type)) {
                content.append(log).append("\n");
            }
            logs.put(type, content.toString());
        }

        return logs;
    }

    //Get the log lists and then display.
    public HashMap<String, ArrayList<String>> getLogsList() throws RuntimeException{
        ArrayList<String> logLines = getLogFileLines();

        HashMap<String, ArrayList<String>> logs = new HashMap<>();
        for (String line : logLines) {
            // check if string contains illegal character
            if (line.contains("�")) {
                continue;
            }
            String[] parts = line.split(",");
            String type    = parts[0];

            // check if type exists in logs
            logs.putIfAbsent(type, new ArrayList<>());
            logs.get(type).add(line);
        }

        return logs;
    }

    //Get the lines from the log files and put them into the log screen.
    private ArrayList<String> getLogFileLines() throws RuntimeException {
        File logFile = getLogFileFromMicrobit();
        ArrayList<String> logFileLines = new ArrayList<>();
        try {
            String logFileContents = new String(Files.readAllBytes(Path.of(logFile.getAbsolutePath())));
            String[] logFileLinesArray = logFileContents.split("\n");
            logFileLines.addAll(Arrays.asList(logFileLinesArray));

            for (int i = 0; i < 4; i++) {
                logFileLines.remove(0);
            }
            logFileLines.remove(logFileLines.size() - 1);

            return logFileLines;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Check the logfiles received from the microbit.
    private File getLogFileFromMicrobit() throws RuntimeException{
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

        throw new RuntimeException("No log file found");
    }
}
