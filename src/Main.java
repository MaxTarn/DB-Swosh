//add users by console
//delete users by console
//log in
//add account
//delete accound
//

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
   //uppdatera all användar upgifter?
   //förklara vad modell ska göra
   static MysqlDataSource dataSource = new MysqlDataSource();
   static Connection connection;
   static Statement statement;
   static PreparedStatement prepStatement;
   static Session currSession = new Session();

   //path to external file
   static String creddentialsPath = "src/credentials.properties";


   public static void main(String[] args) throws Exception {
      init(creddentialsPath);

      Boolean wantsContinue = true;

      while(wantsContinue){
         System.out.println("----------");
         System.out.println("What do you want to do?");
         System.out.println("1: Log in");
         System.out.println("2: Add user");
         System.out.println("3: Edit user details");
         System.out.println("4: Delete user");
         System.out.println("5: Add a money account");
         System.out.println("6: Remove a money account");
         System.out.println("7: Summarize a user & their accounts");
         System.out.println("Requires log in:");
         System.out.println("8: Send Money");
         System.out.println("9: Steal Money");

         System.out.println("----------");

         int option = Terminal.askForInt("Option:");
         switch (option){
            case 1:
               UserActions.logIn(currSession);
               break;
            case 2:
               UserActions.addUser();
               break;
            case 3:
               UserActions.editUser();
               break;
            case 4:
               UserActions.deleteUser();
               break;
            case 5:
               UserActions.addAccount();
               break;
            case 6:
               UserActions.removeAccount();
               break;
            case 7:
               break;
            case 8:
               break;
            case 9:
               break;
         }
      }




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


   static void close(){
      Terminal.close();
   }






}