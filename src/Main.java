import com.mysql.cj.jdbc.MysqlDataSource;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Main {
   //uppdatera all användar upgifter?
   //förklara vad modell ska göra
   static MysqlDataSource dataSource = new MysqlDataSource();
   static Connection connection;
   static Statement statement;
   static PreparedStatement prepStatement;

   //path to external file
   static String creddentialsPath = "src/credentials.properties";


   public static void main(String[] args) throws Exception {
      Users.getAll()

      init(creddentialsPath);
      prepStatement = connection.prepareStatement("INSERT INTO users (`person_num`, `password`, `created`, `user_name`) values(?, ?, NOW(),?);");
      String personNum = Terminal.askForNotEmptyString("Person nummer:");
      String password = Terminal.askForNotEmptyString("Password:");
      String userName = Terminal.askForNotEmptyString("User Name:");

      String hashedPersonNum = hashIt(personNum);
      String hashedPassword = hashIt(password);

      prepStatement.setString(1, hashedPersonNum);
      prepStatement.setString(2, hashedPassword);
      prepStatement.setString(3, userName);
      prepStatement.execute();

      PreparedStatement getAllUsers = connection.prepareStatement("SELECT * FROM users");
      ResultSet allUsers = getAllUsers.executeQuery();
      while(allUsers.next()){
         int id = allUsers.getInt("id");
         String hashedPersonNumber = allUsers.getString("person_num");
         String hashedpassword = allUsers.getString("password");
         System.out.println("id: " + id);
         System.out.println("hashed person number: " + hashedPersonNumber);
         System.out.println("hashed password: " + hashedpassword);

      }


   }












   //inits the communication with the database
   static void init(String credentialsPath) throws Exception {
      Credentials.init(credentialsPath);

      //configuring dataSource
      try {
         System.out.print("Configuring data source...");
         dataSource.setUser(Credentials.getUsername());
         dataSource.setPassword(Credentials.getPassword());
         dataSource.setUrl(Credentials.getUrl());
         dataSource.setUseSSL(false);

         System.out.print("done!\n");
      } catch(SQLException e){
         System.out.print("failed!\n");
         System.exit(0);
      }


      try{
         System.out.print("Fetching connection to database...");
         connection = dataSource.getConnection();
         Model.setConnection(connection);
         statement = connection.createStatement();

         System.out.print("done!\n");

      }
      catch(SQLException e){
         System.out.print("failed!\n");
         System.exit(0);
      }

   }





   //---------- Hashing methods ----------

   //hashes a string
   public static String hashIt(String stringToHash){
      return BCrypt.hashpw(stringToHash, BCrypt.gensalt(15));
   }

   //compares an unhashed string to a hashed string,
   // returns true if the hashedString was originally the same string as the notHashedString
   public static Boolean compare(String notHashedString, String hashedString){return BCrypt.checkpw(notHashedString, hashedString);}
   //---------- Hashing methods END----------

}