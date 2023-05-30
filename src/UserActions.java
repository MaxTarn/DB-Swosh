import java.sql.ResultSet;

public class UserActions {

   public static void logIn(Session currSession){
      String inputUserName = Terminal.askForNotEmptyString("User Name");
      String inputPersonNumber = String.valueOf(Terminal.askForInt("Person Number: "));
      String inputPassword = Terminal.askForNotEmptyString("Password:");
      ResultSet usersWithThatUserName = Users.selectAllByUserName(inputUserName);

      try {
         while(usersWithThatUserName.next()){

            String dbPersonNum = usersWithThatUserName.getString("person_num"); //it is hashed
            String dbPassword = usersWithThatUserName.getString("password");  //it is hashed

            //validates person number and password
            if(!Hasher.compare(inputPersonNumber, dbPersonNum))continue;
            if(!Hasher.compare(inputPassword, dbPassword))continue;

            //this only runs when password and personNumber is validated
            currSession.logIn();
            currSession.setUserID(usersWithThatUserName.getInt("id"));
            currSession.setCreated(usersWithThatUserName.getDate("created"));
            currSession.setUserName(usersWithThatUserName.getString("user_name"));
            currSession.setHashedPassWord(dbPassword);
            currSession.setHashedPersonNum(dbPersonNum);
         }
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      System.out.println("Contacted database");

      //greets the user by their username
      if(currSession.isLoggedIn()) System.out.println("Welcome " + currSession.getUserName());
   }

   public static void addUser(){

      String personNum = String.valueOf(Terminal.askForInt("Person nummer:"));
      String password = Terminal.askForNotEmptyString("Password:");
      String userName = Terminal.askForNotEmptyString("User Name:");

      String hashedPersonNum = Hasher.hashIt(personNum);
      String hashedPassword = Hasher.hashIt(password);

      Users.addUserToDB(hashedPersonNum, hashedPassword, userName);

   }
   public static void editUser(){
      String userName = Terminal.askForNotEmptyString("User name:");
      ResultSet response = Users.selectAllByUserName(userName);

      int userCount = 0;
      try{
         while(response.next()){
            userCount++;
         }
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
   }
}
