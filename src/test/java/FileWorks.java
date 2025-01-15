import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWorks {
    public static void writeToFile(String text, String fileName) throws IOException {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(text);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
