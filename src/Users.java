import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users extends Model{



   public ResultSet getAll() throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
      return preparedStatement.executeQuery();
   }
   public static ResultSet selectAllByUserName(String userName){
      PreparedStatement prepState = null;
      try{
         prepState = connection.prepareStatement("SELECT * FROM users WHERE user_name=?");
         prepState.setString(1, userName);
         System.out.println("Contacting Database...");
         ResultSet response = prepState.executeQuery();
         return response;
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return null;
   }
   public static void addUserToDB(String personNum, String password, String userName){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("INSERT INTO users (`person_num`, `password`, `created`, `user_name`) values(?, ?, NOW(),?);");
         prepState.setString(1, personNum);
         prepState.setString(2, password);
         prepState.setString(3, userName);
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }

   }

}
