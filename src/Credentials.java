import java.awt.image.DataBufferDouble;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.Buffer;
import java.util.Properties;

public class Credentials {
   static File path;
   static String host;
   static int port;
   static String database;
   static String userName;
   static String password;
   static String timeZone;
   static String url;

   public static String getHost() {
      return host;
   }

   public static int getPort() {
      return port;
   }

   public static String getDatabase() {
      return database;
   }

   public static String getUsername() {
      return userName;
   }

   public static String getPassword() {
      return password;
   }

   public static String getUrl() {
      return url;
   }


   public static void init(String filePath) throws Exception {
      try{
         path = new File(filePath);
      }catch (Exception ex){
         throw new Exception("File Path for Credentials is invalid");
      }

      readFile();
   }

   //reads from a file, and sets the variables @ line 10-17
   private static void readFile(){
      Properties properties = new Properties();
      FileInputStream fileInStream = null;
      try {
         fileInStream = new FileInputStream(path);
         properties.load(fileInStream);

         port = Integer.parseInt(properties.getProperty("port"));
         host = properties.getProperty("host");
         database = properties.getProperty("database");
         userName = properties.getProperty("username");
         password = properties.getProperty("password");
         timeZone = properties.getProperty("timezone");
         url = properties.getProperty("urlstart") + host + ":" + port + "/" + database + timeZone;

      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }

}
