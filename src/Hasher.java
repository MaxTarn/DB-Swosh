import org.mindrot.jbcrypt.BCrypt;

public class Hasher {
   //class is just used to that it is a bit more readable in code

   //---------- Hashing methods ----------

   //hashes a string
   public static String hashIt(String stringToHash){
      return BCrypt.hashpw(stringToHash, BCrypt.gensalt(15));
   }

   //compares an unhashed string to a hashed string,
   // returns true if the hashedString was originally the same string as the notHashedString
   public static Boolean compare(String notHashedString, String hashedString){return BCrypt.checkpw(notHashedString, hashedString);}
   //---------- Hashing methods END----------
}
