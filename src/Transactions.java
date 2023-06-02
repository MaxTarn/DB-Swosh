import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
   public static void sendMoney(int fromAccountId, int toAccountId, double amount){

   }
}
