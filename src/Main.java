//add users by console
//delete users by console
//log in
//add account
//delete accound
//

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
   static MysqlDataSource dataSource = new MysqlDataSource();
   static Connection connection;
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
         System.out.println("1:  Log in");
         System.out.println("2:  Add user");
         System.out.println("3:  Edit user details");
         System.out.println("4:  Delete user");
         System.out.println("5:  Add a money account");
         System.out.println("6:  Remove a money account");
         System.out.println("7:  Summarize a user & their accounts");
         System.out.println("Requires log in:");
         System.out.println("8:  Send Money");
         System.out.println("9:  Receive Money");
         System.out.println("10: List transaction for an account between two dates");

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
               UserActions.sumerizeUser();
               break;
            case 8:
               if(!currSession.loggedIn)UserActions.logIn(currSession);
               UserActions.sendMoney(currSession);
               break;
            case 9:
               if(!currSession.loggedIn)UserActions.logIn(currSession);
               break;
            case 10:
               if(!currSession.loggedIn)UserActions.logIn(currSession);
               break;
         }
      }


      close();
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

         System.out.print("done!\n");

      }
      catch(SQLException e){
         System.out.print("failed!\n");
         System.exit(0);
      }

   }


   static void close() {
      Terminal.close();
      try {
         Model.connection.close();
      } catch (Exception ex) {
      }
   }


}