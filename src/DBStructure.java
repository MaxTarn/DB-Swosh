public class DBStructure {
   String dbStructure= "/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;\n" +
           "/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;\n" +
           "/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;\n" +
           "/*!40101 SET NAMES utf8mb4 */;\n" +
           "/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;\n" +
           "/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;\n" +
           "/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;\n" +
           "/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;\n" +
           "\n" +
           "CREATE TABLE `acounts` (\n" +
           "  `id` bigint unsigned NOT NULL AUTO_INCREMENT,\n" +
           "  `user_id` int NOT NULL,\n" +
           "  `amount` double NOT NULL,\n" +
           "  UNIQUE KEY `id` (`id`)\n" +
           ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
           "\n" +
           "CREATE TABLE `transactions` (\n" +
           "  `id` bigint unsigned NOT NULL AUTO_INCREMENT,\n" +
           "  `time` datetime NOT NULL,\n" +
           "  `from_acount_id` int NOT NULL,\n" +
           "  `to_acount_id` int NOT NULL,\n" +
           "  `amount` double NOT NULL,\n" +
           "  UNIQUE KEY `id` (`id`)\n" +
           ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
           "\n" +
           "CREATE TABLE `users` (\n" +
           "  `id` int NOT NULL AUTO_INCREMENT,\n" +
           "  `person_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
           "  `password` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
           "  `created` datetime NOT NULL,\n" +
           "  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,\n" +
           "  PRIMARY KEY (`id`)\n" +
           ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;\n" +
           "\n" +
           "\n" +
           "\n" +
           "/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;\n" +
           "/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;\n" +
           "/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;\n" +
           "/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;\n" +
           "/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;\n" +
           "/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;\n" +
           "/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;";

}
