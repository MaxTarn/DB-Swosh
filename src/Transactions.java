import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class Transactions extends Model{
   @Override
   public ResultSet getAll()   {
      try {
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transactions");
         return preparedStatement.executeQuery();
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return null;
   }
   //TODO
   public static void recordTransaction(int fromAccountId, int toAccountId, double amount){
      try{
         PreparedStatement prepState = connection.prepareStatement("INSERT INTO transactions (time, from_account_id, to_account_id, amount) VALUES(NOW(), ?, ?, ?)");
         prepState.setInt(1, fromAccountId);
         prepState.setInt(2, toAccountId);
         prepState.setDouble(3, amount);
         prepState.execute();
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   public static ResultSet getTransactionHistory(Date from, Date to, int accountId){
      try {
         PreparedStatement prepState = connection.prepareStatement("""
                 SELECT * FROM transactions
                 WHERE time >= ? AND time <= ? AND (from_account_id = ? OR to_account_id = ?)
                 ORDER BY amount DESC;""");
         prepState.setDate(1, new java.sql.Date(from.getTime()));
         prepState.setDate(2, new java.sql.Date(to.getTime()));
         prepState.setInt(3, accountId);
         prepState.setInt(4, accountId);
         return prepState.executeQuery();
      }catch (Exception ex){
         System.out.println(Arrays.toString(ex.getStackTrace()));
      }
      return null;
   }
}
