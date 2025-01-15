package qa.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The class implements the Properties Factory pattern and covers work with Properties for the project
 */
public class PropertiesFactory {
    private static Properties properties;
    private static FileInputStream fileInputStream;

    /**
     * Block for reading properties from a file
     */
    static {
        try {
            fileInputStream = new FileInputStream("src/main/resources/project.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null){
                try{
                    fileInputStream.close();
                } catch (IOException e){
                    throw new RuntimeException();
                }
            }
        }
    }

    /**
     * Gets properties by key
     * @param key - the string by which the value is returned
     * @return value as a string
     */
    public static String getProperty(String key){
        return properties.getProperty(key);
    }

    /**
     * Get Default browser for project
     * @return browser as string from properties
     */
    public static String getBrowserProperty(){
        return properties.getProperty("browser");
    }

    /**
     * Get MainUrl for project
     * @return main url as string from properties
     */
    public static String getMainURLProperty(){
        return properties.getProperty("URL");
    }
}