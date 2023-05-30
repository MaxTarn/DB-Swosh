public class UserActions {

   public static void printOptions(){
      System.out.println("What do you want to do?");
      System.out.println("");
   }
   public void logIn(){
      String userName = Terminal.askForNotEmptyString("User Name");
      int personNumber = Terminal.askForInt("Person Number: ");
      String password = Terminal.askForNotEmptyString("Password:");
   }
}
