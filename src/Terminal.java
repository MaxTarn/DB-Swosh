import java.util.Scanner;

public class Terminal {
   static Scanner console = new Scanner(System.in);

   public static String getInput(){
      return console.nextLine().trim();
   }
   public static String getNotEmptyString(){
      String input = getInput();
      while (input.isEmpty() ) input = getInput();
      return input;
   }
   public static String askForNotEmptyString(String question){
      System.out.print(question +" ");
      return getNotEmptyString();
   }
}
