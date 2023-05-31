import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Terminal {
   static Scanner console = new Scanner(System.in);

   public static String getInput(){
      return console.nextLine().trim();
   }


   //--------------------Methods that can get or diplay a certain type of data from the console --------------------

   //---------- string ----------
   public static String getString(){
      String input = getInput();
      return input;
   }
   public static String askForString(String question){
      System.out.println(question + " ");
      String input = getString();
      return input;
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
      double input = Double.parseDouble(getInput());
      return input;
   }
   public static double askForDouble(String question){
      System.out.print(question + " ");
      return getDouble();
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
   public static Date getDate(){
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String input = getNotEmptyString();
      try {
         Date date = dateFormat.parse(input);
         return date;
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }
      return null;
   }
   public static Date askForDate(String question){
      System.out.print(question + "(format (yyyy-MM-dd HH:mm:ss) ) ");
      return getDate();
   }


   public static String askForDateAsString(String question){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(askForDate(question));

      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;  // why is month is zero-based???
      int day = calendar.get(Calendar.DAY_OF_MONTH);
      int hour = calendar.get(Calendar.HOUR_OF_DAY);
      int minute = calendar.get(Calendar.MINUTE);
      int second = calendar.get(Calendar.SECOND);

      return year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
   }
   //---------- Date END----------



   //--------------------Methods that can get or diplay a certain type of data from the console END--------------------

   public static void close(){
      console.close();
   }
}
