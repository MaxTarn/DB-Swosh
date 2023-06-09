import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users extends Model{



   public ResultSet getAll()   {
      try {
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users;");
         return preparedStatement.executeQuery();
      }catch (Exception ignored){}
      return null;
   }
   public static Boolean exists(String userName){
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT CASE WHEN EXISTS (SELECT 1 FROM users WHERE user_name = ?) THEN 1 ELSE 0 END AS user_exists");
         prepState.setString(1, userName);
         ResultSet response = prepState.executeQuery();
         Boolean exists = null;
         if(response.next()) exists = response.getBoolean("user_exists");
         return exists;
      }catch (Exception ex) {System.out.println(ex.getMessage());}
      return false;
   }
   public static ResultSet getUser(String userName){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("SELECT * FROM users WHERE user_name=?;");
         prepState.setString(1, userName);
         System.out.println("Contacting Database...");
         return prepState.executeQuery();
      }catch (Exception ex){System.out.println(ex.getMessage());}
      return null;
   }
   public static Integer getIdByUserName(String userName){
      if(!Users.exists(userName))return null;
      try{
         PreparedStatement prepState = connection.prepareStatement("SELECT id FROM users WHERE user_name=?");
         prepState.setString(1, userName);
         ResultSet response = prepState.executeQuery();
         Integer id = null;
         while(response.next())id = response.getInt("id");
         return id;
      }catch (Exception ex){System.out.println(ex.getMessage());}
      return null;
   }




   public static void addUserToDB(String personNum, String password, String userName){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("INSERT INTO users (`person_num`, `password`, `created`, `user_name`) values(?, ?, NOW(),?);");
         prepState.setString(1, personNum);
         prepState.setString(2, password);
         prepState.setString(3, userName);
         prepState.executeUpdate();
      }catch (Exception ex){System.out.println(ex.getMessage());}

   }
   public static void updatePersonNum(int id, String newPersonNum){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("UPDATE users SET person_num=? WHERE id=?;");
         prepState.setString(1,newPersonNum);
         prepState.setInt(2, id);
         prepState.execute();
      }catch (Exception ex){System.out.println(ex.getMessage());}
   }
   public static void updatePassword(int id, String newPassword){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("UPDATE users SET password=? WHERE id=?;");
         prepState.setString(1,newPassword);
         prepState.setInt(2, id);
         prepState.execute();
      }catch (Exception ex){System.out.println(ex.getMessage());}
   }
   public static void updateUserName(int id, String newUserName){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("UPDATE users SET user_name=? WHERE id=?;");
         prepState.setString(1,newUserName);
         prepState.setInt(2, id);
         prepState.execute();
      }catch (Exception ex){System.out.println(ex.getMessage());}
   }
   public static void deleteUser(String userName){
      PreparedStatement prepState;
      try{
         prepState = connection.prepareStatement("DELETE FROM users WHERE user_name=?;");
         prepState.setString(1, userName);
         prepState.execute();
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
}
