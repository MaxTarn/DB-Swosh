import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users extends Model{


   @Override
   public ResultSet getAll() throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
      return preparedStatement.executeQuery();
   }
}
