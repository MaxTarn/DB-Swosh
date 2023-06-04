import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class Accounts extends Model{
   @Override
   public ResultSet getAll() {
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT * FROM accounts");
         return prepState.executeQuery();
      }catch (Exception ex){System.out.println(Arrays.toString(ex.getStackTrace()));}
      return null;
   }
   public static int getAmountbyId(int accountId){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT amount FROM accounts WHERE id=?");
         prepState.setInt(1, accountId);
         ResultSet response = prepState.executeQuery();
         response.next();
         return response.getInt("amount");
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }
      return -1;
   }
   public static ResultSet getAllAccountsByUserId(int userId){
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT * FROM accounts WHERE user_id=?");
         prepState.setInt(1, userId);
         return prepState.executeQuery();
      }catch (Exception ex){System.out.println(Arrays.toString(ex.getStackTrace()));}
      return null;
   }
   public static Boolean userHasAccount(int userID){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT EXISTS(SELECT 1 FROM accounts WHERE user_id=?) AS exists_row;");
         prepState.setInt(1, userID);
         Boolean result = prepState.execute();
         prepState.close();
         return result;
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }
      return false;
   }
   public static Boolean accountExists(int accountId){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT CASE WHEN EXISTS (SELECT 1 FROM accounts WHERE id=?) THEN 1 ELSE 0 END AS user_exists");
         prepState.setInt(1, accountId);
         ResultSet response = prepState.executeQuery();
         Boolean exists = null;
         if(response.next()) exists = response.getBoolean("user_exists");
         prepState.close();
         return exists;
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }
      return null;
   }

   public static Boolean accountBelongsTo(int accountId, int userId){
      if(Boolean.FALSE.equals(accountExists(accountId)))return false;
      if(!userHasAccount(userId))return false;

      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT COUNT(*) AS count FROM accounts WHERE id = ? AND user_id = ?");
         prepState.setInt(1, accountId);
         prepState.setInt(2, userId);
         ResultSet resultSet = prepState.executeQuery();
         resultSet.next();
         return resultSet.getInt("count") > 0;
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }

      return false;
   }
   //gets the number of accounts a user has
   public static int getAccountCount(int userId) {
      if(!userHasAccount(userId))return 0;
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT COUNT(*) AS count FROM accounts WHERE user_id = ?");
         prepState.setInt(1, userId);
         ResultSet response = prepState.executeQuery();
         prepState.close();
         response.next();
         return response.getInt("count");
      } catch (Exception ex) {System.out.println(Arrays.toString(ex.getStackTrace()));}
      return 0;
   }

   //adds account
   public static void addAccount(int userId, int amount ){
      try{
         PreparedStatement prepState = connection.prepareStatement("INSERT INTO accounts (user_id, amount) VALUES(?,?);");
         prepState.setInt(1, userId);
         prepState.setInt(2, amount);
         prepState.execute();
         System.out.println("Added account to user");
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));}
   }

   //removes account
   public static void deleteAccount(int accountId){
      try{
         PreparedStatement prepState = connection.prepareStatement("DELETE FROM accounts WHERE id=?");
         prepState.setInt(1, accountId);
         prepState.execute();
         System.out.println("Account Deleted.");
         prepState.close();
      }catch (Exception ignored){

      }
   }
   //checkes if the amount is ATLEAST
   public static Boolean hasAtleastAmount(int accountId, double amount){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT CASE WHEN amount >= ? THEN 1 ELSE 0 END AS meets_minimum_amount FROM accounts WHERE id = ?");
         prepState.setDouble(1, amount);
         prepState.setInt(2, accountId);
         ResultSet response = prepState.executeQuery();
         response.next();
         int queryResponse = response.getInt("meets_minimum_amount");
         return queryResponse == 1;
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
         return false;
      }
   }
   //removes money from an account
   public static void takeMoneyFromAccount(int accountId, double amount ){
      if(Boolean.FALSE.equals(accountExists(accountId)))return;
      try{
         PreparedStatement prepState = connection.prepareStatement("UPDATE accounts SET amount = amount - ? WHERE id = ?");
         prepState.setDouble(1, amount);
         prepState.setInt(2, accountId);
         prepState.execute();
         prepState.close();
      }catch (Exception ex) {
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }

   }
   //adds money to an account
   public static void addMoneyToAccount(int accountId, double amount){
      if(Boolean.FALSE.equals(accountExists(accountId)))return;
      try {
         PreparedStatement prepState = connection.prepareStatement("UPDATE accounts SET amount = amount + ? WHERE id = ?");
         prepState.setDouble(1, amount);
         prepState.setInt(2, accountId);
         prepState.executeUpdate();
         prepState.close();
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }
   }
}
