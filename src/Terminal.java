import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public class Terminal {
   static Scanner console = new Scanner(System.in);

   public static String getInput(){
      return console.nextLine();
   }


   //--------------------Methods that can get or diplay a certain type of data from the console --------------------

   //---------- string ----------
   public static String getString(){
      return getInput();
   }
   public static String askForString(String question){
      System.out.println(question + " ");
      return getString();
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
   //---------- string END----------


   //---------- double ----------
   public static double getDouble(){
      return Double.parseDouble(getInput());
   }
   public static double askForDouble(String question){
      System.out.print(question + " ");
      double input = -1;
      boolean goodInput = false;
      while(!goodInput){
         try {
            input = getDouble();
            goodInput = true;
         }catch (Exception ex){
            System.out.print("(Invalid input)" + question);
         }
      }
      return input;
   }
   //---------- double END----------


   //---------- int ----------
   public static int getInt(){
      return Integer.parseInt(getInput());
   }
   public static int askForInt(String question){
      System.out.print(question + " ");
      Integer input = null;
      boolean goodInput = false;

      while(!goodInput){
         try {
            input = getInt();
            goodInput = true;
         }catch (Exception ex){
            System.out.print("(Invalid input)" + question);
         }
      }
      return input;
   }
   //---------- int END----------



   //---------- Date ----------
   public static Date askForDate(String question) {
      System.out.println("(format: yyyy-MM-dd HH:mm:ss) " + question);
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date;

      while (true) {
         System.out.print(question);
         String userInput = console.nextLine();

         try {
            java.util.Date utilDate = dateFormat.parse(userInput);
            date = new Date(utilDate.getTime());
            return date;
         } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
         }
      }
   }
   //---------- Date END----------



   //--------------------Methods that can get or diplay a certain type of data from the console END--------------------

   public static void close(){
      console.close();
   }
}
