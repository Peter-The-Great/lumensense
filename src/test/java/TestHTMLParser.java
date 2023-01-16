import org.jsoup.Jsoup;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

public class TestHTMLParser {
    public static void main(String[] args) {
        File logFile = getLogFileFromMicrobit();
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
