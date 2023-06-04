import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;

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

      int personNum = Terminal.askForInt("Person number:");
      String password = Terminal.askForNotEmptyString("Password:");
      String userName = Terminal.askForNotEmptyString("User Name:");

      //handles when there is already a user in DB with that userName
      if(Boolean.TRUE.equals(Users.exists(userName))){
         System.out.println("User with that user name already exists.");
         addUser();
         return;
      }

      String hashedPersonNum = Hasher.hashIt(String.valueOf(personNum));
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

      while (Users.exists(newUserName))newUserName = Terminal.askForString("(User name taken) New user name:");

      if(!newPersonNum.equals(""))Users.updatePersonNum(userId, Hasher.hashIt(newPersonNum));
      if(!newPassword.equals(""))Users.updatePassword(userId, Hasher.hashIt(newPassword));
      if(!newUserName.equals(""))Users.updateUserName(userId, newUserName);
      System.out.println("Updated the field(s) you wanted");
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

   }
   private static Boolean userExistsAndHasAccount(String userName){
      if(!Users.exists(userName)) return false;
      int userId = Users.getIdByUserName(userName);
      if(!Accounts.userHasAccount(userId))return false;
      return true;

   }
   private static Boolean accountIsRemoveAble(int userId, int id){
      if(Boolean.FALSE.equals(Accounts.userHasAccount(userId)))return false;
      if(Boolean.FALSE.equals(Accounts.userHasAccount(userId)))return false;
      if(Boolean.FALSE.equals(Accounts.accountExists(id)))return false;
      return true;
   }
   public static void removeAccount(){
      String userName = Terminal.askForNotEmptyString("Remove account from user:");

      while(!userExistsAndHasAccount(userName)){
         userName = Terminal.askForNotEmptyString("(User does not exist or does not have an account) User name:");
      }

      ResultSet allAccounts = Accounts.getAllAccountsByUserId(Users.getIdByUserName(userName));
      try{
         System.out.println("The accounts of" + userName+  ":");
         System.out.println("---");
         while(allAccounts.next()){
            System.out.println("Account number: " + allAccounts.getInt("id"));
            System.out.println("Monies: " + allAccounts.getInt("amount"));
            System.out.println("---");
         }
      }catch (Exception ex){System.out.println(ex.getMessage());}
      int accountToRemove = Terminal.askForInt("What account do you wish to remove:");
      while(!accountIsRemoveAble(Users.getIdByUserName(userName), accountToRemove)) accountToRemove= Terminal.askForInt("(invalid input) Enter the account number:");
      Accounts.deleteAccount(accountToRemove);
   }
   public static void sumerizeUser(){
      String userName = Terminal.askForNotEmptyString("User name:");
      while (!Users.exists(userName))userName = Terminal.askForString("(No user by that name) User name:");
      ResultSet user = Users.getUser(userName);
      try{
         user.next();
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         System.out.println("-----");
         System.out.println("---");
         System.out.println("User name: " + user.getString("user_name") );
         System.out.println("Created: " + format.format(user.getDate("created")));
         System.out.println("---");
      }catch (Exception ex){System.out.println(ex.getMessage());}

      ResultSet userAccounts = Accounts.getAllAccountsByUserId(Users.getIdByUserName(userName));
      try {
         while(userAccounts.next()){
            System.out.println("Account number: " + userAccounts.getInt("id"));
            System.out.println("Monies: " + userAccounts.getInt("amount"));
            System.out.println("---");
         }
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      System.out.println("-----");

   }
   public static void sendMoney(Session currSession){
      if(!currSession.isLoggedIn()) return;
      if(!Accounts.userHasAccount(currSession.getUserID())) return;

      int fromAccountId = Terminal.askForInt("Your account number:");
      while(!Accounts.accountBelongsTo(fromAccountId, currSession.getUserID())){
         fromAccountId = Terminal.askForInt("Invalid number. Try again:");
      }


      int toAccountId = Terminal.askForInt("Send to account number:");
      while(Boolean.FALSE.equals(Accounts.accountExists(toAccountId))){
         toAccountId = Terminal.askForInt("Invalid account. Try again:");
      }


      double amountToSend = Terminal.askForDouble("Amount to send:");
      while(!Accounts.hasAtleastAmount(toAccountId, amountToSend)){
         amountToSend = Terminal.askForDouble("Invalid amount. Try again:");
      }


      try{
         Accounts.takeMoneyFromAccount(fromAccountId, amountToSend);
         Accounts.addMoneyToAccount(toAccountId,amountToSend);
         Transactions.recordTransaction(fromAccountId, toAccountId, amountToSend);
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   public static void takeMoney(Session currSession){
      if(!currSession.isLoggedIn()) return;
      if(!Accounts.userHasAccount(currSession.getUserID())) return;

      int toAccountId = Terminal.askForInt("Your account number:");
      while(!Accounts.accountBelongsTo(toAccountId, currSession.getUserID())){
         toAccountId = Terminal.askForInt("Invalid number. Try again:");
      }


      int fromAccountId = Terminal.askForInt("Take from account number:");
      while(Boolean.FALSE.equals(Accounts.accountExists(fromAccountId))){
         fromAccountId = Terminal.askForInt("Invalid number. Try again:");
      }


      double amountToTake = Terminal.askForDouble("Amount to take:");
      while(!Accounts.hasAtleastAmount(fromAccountId, amountToTake)){
         amountToTake = Terminal.askForDouble("Invalid amount. Try again:");
      }

      try{
         Accounts.takeMoneyFromAccount(fromAccountId, amountToTake);
         Accounts.addMoneyToAccount(toAccountId,amountToTake);
         Transactions.recordTransaction(fromAccountId, toAccountId, amountToTake);
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   public static void listTransactions(Session currSession){
      if(Boolean.FALSE.equals(Accounts.userHasAccount(currSession.getUserID())))return;

      int accountId = Terminal.askForInt("Your Account number:");
      while(Boolean.FALSE.equals(Accounts.accountExists(accountId)) || !Accounts.accountBelongsTo(accountId, currSession.getUserID())){
         accountId = Terminal.askForInt("invalid number. Try again:");
      }

      Date startingDate = Terminal.askForDate("Starting date:");
      Date endingDate = Terminal.askForDate("Ending date:");

      while(endingDate.before(startingDate)){
         endingDate = Terminal.askForDate("(ending date cannot be before the starting date... duh) try again:");
      }
      ResultSet response = Transactions.getTransactionHistory(startingDate, endingDate, accountId);

      try {
         while(response.next()){
            Timestamp time = response.getTimestamp("time");
            int fromAccount = response.getInt("from_account_id");
            int toAccount = response.getInt("to_account_id");
            double amount = response.getDouble("amount");
            if(fromAccount == accountId){

               System.out.println("Transaction sent on date " + time + ", to account number " + toAccount + ", totaling: " + amount);
            }else if(toAccount == accountId){
               System.out.println("Transaction received on date "+ time + ", from account number " + fromAccount + ", totaling: " + amount);
            }


         }
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }

   }
}
