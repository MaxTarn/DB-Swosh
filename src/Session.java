import java.util.Date;

public class Session {
   Boolean loggedIn = false;
   int userID;
   String userName;
   String hashedPersonNum;
   String hashedPassWord;
   Date created;


   public Boolean isLoggedIn() {
      return loggedIn;
   }

   public void logIn() {
      loggedIn = true;
   }

   public int getUserID() {
      return userID;
   }

   public void setUserID(int userID) {
      this.userID = userID;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getHashedPersonNum() {
      return hashedPersonNum;
   }

   public void setHashedPersonNum(String hashedPersonNum) {
      this.hashedPersonNum = hashedPersonNum;
   }

   public String getHashedPassWord() {
      return hashedPassWord;
   }

   public void setHashedPassWord(String hashedPassWord) {
      this.hashedPassWord = hashedPassWord;
   }

   public Date getCreated() {
      return created;
   }

   public void setCreated(Date created) {
      this.created = created;
   }
}
