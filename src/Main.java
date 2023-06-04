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

   static Session currSession = new Session();

   //path to external file
   static String creddentialsPath = "src/credentials.properties";


   //user name number is both the password and the person number
   //ex. user7's person number = 7 , user7's password = 7
   public static void main(String[] args) throws Exception {
      init(creddentialsPath);

      boolean wantsContinue = true;

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
         System.out.println("11: Exists the program");

         System.out.println("----------");

         int option = Terminal.askForInt("Option:");
         switch (option) {
            case 1 -> {
               if (currSession.isLoggedIn()) {
                  System.out.println("You are already logged in");
                  return;
               }
               UserActions.logIn(currSession);
            }
            case 2 -> UserActions.addUser();
            case 3 -> UserActions.editUser();
            case 4 -> UserActions.deleteUser();
            case 5 -> UserActions.addAccount();
            case 6 -> UserActions.removeAccount();
            case 7 -> UserActions.sumerizeUser();
            case 8 -> {
               if (!currSession.loggedIn) {
                  System.out.println("Log in.");
                  UserActions.logIn(currSession);
               }
               UserActions.sendMoney(currSession);
            }
            case 9 -> {
               if (!currSession.loggedIn) {
                  System.out.println("Log in.");
                  UserActions.logIn(currSession);
               }
               UserActions.takeMoney(currSession);
            }
            case 10 -> UserActions.listTransactions(currSession);
            case 11 -> wantsContinue = false;
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
         System.out.println(e.getMessage());
      }

      //checks if there is a table called users
      //and if not assumes that all other tables are not there either
      //creates tables and then inserts some placeholder data
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'users'");
      if (!resultSet.next()) {
         DBStructure.makeTables(connection);
         DBStructure.insert(connection);
      }
      resultSet.close();
   }


   static void close() {
      Terminal.close();
      try {
         Model.connection.close();
      } catch (Exception ignored) {
      }
   }



}