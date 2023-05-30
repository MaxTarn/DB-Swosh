import java.util.Date;

public class Session {
   public Session(int userID, String userName, String hashedPersonNum, String hashedPassWord, Date created){

   }
   int userID;
   String userName;
   String hashedPersonNum;
   String hashedPassWord;
   Date created;

   public int getUserID() {
      return userID;
   }

   public String getUserName() {
      return userName;
   }

   public String getHashedPersonNum() {
      return hashedPersonNum;
   }

   public String getHashedPassWord() {
      return hashedPassWord;
   }

   public Date getCreated() {
      return created;
   }
}
