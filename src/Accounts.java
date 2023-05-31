import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accounts extends Model{
   @Override
   public ResultSet getAll() {
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT * FROM accounts");
         return prepState.executeQuery();
      }catch (Exception ex){System.out.println(ex.getMessage());}
      return null;
   }







/*   public static int getAmountbyId(){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT ");
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }*/








   public static ResultSet getAllAccountsByUserId(int userId){
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT * FROM accounts WHERE user_id=?");
         prepState.setInt(1, userId);
         return prepState.executeQuery();
      }catch (Exception ex){System.out.println(ex.getMessage());}
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
         System.out.println(ex.getMessage());
      }
      return null;
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
         System.out.println(ex.getMessage());
      }
      return null;
   }
   public static int getAccountCount(int userId) {
      if(!userHasAccount(userId))return 0;
      try {
         PreparedStatement prepState = connection.prepareStatement("SELECT COUNT(*) AS count FROM accounts WHERE user_id = ?");
         prepState.setInt(1, userId);
         ResultSet response = prepState.executeQuery();
         prepState.close();
         response.next();
         return response.getInt("count");
      } catch (Exception ex) {System.out.println(ex.getMessage());}
      return 0;
   }







   public static void addAccount(int userId, int amount ){
      try{
         PreparedStatement prepState = connection.prepareStatement("INSERT INTO accounts (user_id, amount) VALUES(?,?);");
         prepState.setInt(1, userId);
         prepState.setInt(2, amount);
         prepState.execute();
         System.out.println("Added account to user");
      }catch (Exception ex){
         System.out.println(ex.getMessage());}
   }
   public static void deleteAccount(int accountId){
      try{
         PreparedStatement prepState = connection.prepareStatement("DELETE FROM accounts WHERE id=?");
         prepState.setInt(1, accountId);
         prepState.execute();
         System.out.println("Account Deleted.");
         prepState.close();
      }catch (Exception ex){

      }
   }
}
