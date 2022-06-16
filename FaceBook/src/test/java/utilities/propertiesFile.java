package utilities;

import bases.TestBase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class propertiesFile {

    static Properties prop = new Properties();


    public static void readProperitesFile()
    {
        try {
            InputStream input = new FileInputStream("src/test/java/utilities/config.properties");
            prop.load(input);
            String browsername= prop.getProperty("browser");
            TestBase.browser=browsername;

        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writePropertiesFile()
    {
        try {
            OutputStream output = new FileOutputStream("src/test/java/utilities/config.properties");
            prop.setProperty("result","faile");
            prop.store(output,null);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
