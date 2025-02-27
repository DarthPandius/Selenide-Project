package ge.tbc.itacademy.data.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfigurations {
    private static final Properties properties = new Properties();

    static {
        try(InputStream input = DBConfigurations.class.getClassLoader().getResourceAsStream("database.properties")){
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String getURL(){
        return properties.getProperty("db.url");
    }
    public static String getUsername(){
        return properties.getProperty("db.username");
    }
    public static String getPassword(){
        return properties.getProperty("db.password");
    }
}