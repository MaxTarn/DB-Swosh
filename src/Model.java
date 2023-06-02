import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Model {

   static Connection connection;

   public static void setConnection(Connection conn){
      connection = conn;
   }

   public abstract ResultSet getAll()  ;
}
