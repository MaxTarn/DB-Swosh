import java.sql.ResultSet;

public class UserActions {

   public static void logIn(Session currSession){
      String inputUserName = Terminal.askForNotEmptyString("User Name");
      String inputPersonNumber = String.valueOf(Terminal.askForInt("Person Number: "));
      String inputPassword = Terminal.askForNotEmptyString("Password:");
      ResultSet usersWithThatUserName = Users.getUser(inputUserName);

      try {
         while(usersWithThatUserName.next()){

            String dbPersonNum = usersWithThatUserName.getString("person_num"); //it is hashed
            String dbPassword = usersWithThatUserName.getString("password");  //it is hashed

            //validates person number and password
            if(!Hasher.compare(inputPersonNumber, dbPersonNum))continue;
            if(!Hasher.compare(inputPassword, dbPassword))continue;

            //this only runs when password and personNumber is validated
            currSession.logIn();
            currSession.setUserID(usersWithThatUserName.getInt("id"));
            currSession.setCreated(usersWithThatUserName.getDate("created"));
            currSession.setUserName(usersWithThatUserName.getString("user_name"));
            currSession.setHashedPassWord(dbPassword);
            currSession.setHashedPersonNum(dbPersonNum);
         }
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      System.out.println("Contacted database");

      //greets the user by their username
      if(currSession.isLoggedIn()) System.out.println("Welcome " + currSession.getUserName());
   }

   public static void addUser(){

      String personNum = String.valueOf(Terminal.askForInt("Person nummer:"));
      String password = Terminal.askForNotEmptyString("Password:");
      String userName = Terminal.askForNotEmptyString("User Name:");

      //handles when there is already a user in DB with that userName
      if(Boolean.TRUE.equals(Users.exists(userName))){
         System.out.println("User with that user name already exists.");
         addUser();
         return;
      }

      String hashedPersonNum = Hasher.hashIt(personNum);
      String hashedPassword = Hasher.hashIt(password);

      Users.addUserToDB(hashedPersonNum, hashedPassword, userName);

   }
   public static void editUser(){
      String inputUserName = Terminal.askForNotEmptyString("User name:");
      System.out.println("Checking if there is a user by that user name...");
      boolean userExists = Users.exists(inputUserName);

      if (!userExists){
         System.out.println("No user with that name, enter a different user_name");
         editUser();
         return;
      }

      System.out.println("User found");
      ResultSet response = Users.getUser(inputUserName);
      int userId = 0;
      try{
         while(response.next()){
            userId = response.getInt("id");
         }
      }catch (Exception ex){System.out.println(ex.getMessage());}



      System.out.println("You are allowed to change your person number, password, and user name.");
      System.out.println("Write the new values, if you dont wish to change it simply press enter.");
      String newPersonNum = Terminal.askForString("New Person number:");
      String newPassword = Terminal.askForString("New password:");
      String newUserName = Terminal.askForString("New user name:");

      while (!Users.exists(newUserName))newUserName = Terminal.askForString("(User name taken) New user name:");

      if(!newPersonNum.equals(""))Users.updatePersonNum(userId, Hasher.hashIt(newPersonNum));
      if(!newPassword.equals(""))Users.updatePassword(userId, Hasher.hashIt(newPassword));
      if(!newUserName.equals(""))Users.updateUserName(userId, Hasher.hashIt(newUserName));
      System.out.println("Updated the field you wanted");
   }
   public static void deleteUser(){
      String userName = Terminal.askForNotEmptyString("What user do you want to delete? Enter the user name:");
      while (!Users.exists(userName))userName = Terminal.askForString("(No user by that name) User name:");
      Users.deleteUser(userName);
      System.out.println("Deleted user: " + userName);
   }
   public static void addAccount(){
      String userName = Terminal.askForNotEmptyString("Add account to user:");
      while (!Users.exists(userName))userName = Terminal.askForString("(No user by that name) User name:");
      int startingMoney = Terminal.askForInt("Money in the account:");
      ResultSet user = Users.getUser(userName);
      Integer userId = null;
      try{
         while(user.next()){
            userId = user.getInt("id");
         }
      }catch (Exception ex){System.out.println(ex.getMessage());}

      if(userId == null)return;
      Accounts.addAccount(userId, startingMoney);
      System.out.println("Added account to user:" + userName);
   }
   public static void removeAccount(){
      String userName = Terminal.askForNotEmptyString("Remove acount from user:");
      while (!Users.exists(userName))userName = Terminal.askForString("(No user by that name) User name:");

   }
}
