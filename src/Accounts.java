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
   public static Boolean userHasAccount(int userID){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT EXISTS(SELECT 1 FROM accounts WHERE user_id=?) AS exists_row;");
         prepState.setInt(1, userID);
         return prepState.execute();
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
         return exists;
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return null;
   }
   public static void addAccount(int userId, int amount ){
      try{
         PreparedStatement prepState = connection.prepareStatement("INSERT INTO accounts (user_id, amount) VALUES(?,?);");
         prepState.setInt(1, userId);
         prepState.setInt(2, amount);
         prepState.execute();
      }catch (Exception ex){System.out.println(ex.getMessage());}
   }
   public static void deleteAccount(int accoundId){

   }
}
