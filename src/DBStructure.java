
import java.sql.*;

public class DBStructure {
   public static void makeTables(Connection connection) throws SQLException {
      Statement state = connection.createStatement();
      state.execute("DROP TABLE IF EXISTS `accounts`;");
      state.execute("CREATE TABLE `accounts` (   `id` bigint unsigned NOT NULL AUTO_INCREMENT,   `user_id` int NOT NULL,   `amount` double NOT NULL,   UNIQUE KEY `id` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
      state.execute("DROP TABLE IF EXISTS `transactions`;");
      state.execute("CREATE TABLE `transactions` (   `id` bigint unsigned NOT NULL AUTO_INCREMENT,   `time` datetime NOT NULL,   `from_account_id` int NOT NULL,   `to_account_id` int NOT NULL,   `amount` double NOT NULL,   UNIQUE KEY `id` (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
      state.execute("DROP TABLE IF EXISTS `users`;");
      state.execute("CREATE TABLE `users` (   `id` int NOT NULL AUTO_INCREMENT,   `person_num` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,   `password` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,   `created` datetime NOT NULL,   `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,   PRIMARY KEY (`id`),   UNIQUE KEY `user_name` (`user_name`) ) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;");
      state.close();
   }
   public static void insert(Connection connection) throws SQLException {
      Statement state = connection.createStatement();
      state.execute("INSERT INTO `accounts` (`id`, `user_id`, `amount`) VALUES(17, 13, 600),(18, 14, 300),(19, 15, 4),(20, 16, 470),(21, 17, 600),(22, 18, 30),(23, 20, 0);");
      state.execute("INSERT INTO `users` (`id`, `person_num`, `password`, `created`, `user_name`) VALUES(12, '$2a$15$PDNupIclYdI43qycHrzce.JyG1n1ESREYY7sjQ/uZNcg8e2yZsPA.', '$2a$15$qeH.8doL0BsxgPiI.59S4u6KVE5EEcn4utcJn2quxHUZ/yG1Bz1ZK', '2023-06-04 18:11:02', 'user1'), (13, '$2a$15$EabLHk2UILTzQkdIauLNr.zj6oZo8F5OE6ZFmuLYoKkdIzHdDNFqC', '$2a$15$fz/iafRbu0YAmgM263PQ/OIrXxF9bjE5wEodqJmjk2c5Ix.Rhx.MW', '2023-06-04 18:11:19', 'user2'), (14, '$2a$15$LKNiFjkFXLwc1SYmWcNY7urkMO5mqKzdtnon/0b4grrUL1kp.nPWq', '$2a$15$JpKkGe/QVQo1nabt2YfgpuXuRq5UHQNOiPIrOXgU3vHZ0BZgzPoeO', '2023-06-04 18:11:28', 'user3'), (15, '$2a$15$67syOGUaTxaU/HAG.A9cP.O6wIbihMZH7Nwjp54C71k7ItDkZOJVG', '$2a$15$tx8KLNCk9W3ud5fOTxeZKulaJ5uZ6Jm6Ep8sit9dPaQxrIxOdso1.', '2023-06-04 18:11:39', 'user4'),(16, '$2a$15$wpRopPmPOfYrzI3UFpOiheya7aAkUuR9WDngNiMHFNO.sJ.Ebf.Yy', '$2a$15$eENKm.FTGJMhduDQYR.GuuhCWFjg2hVyfl05cdbb01G5Pib8QrKYm', '2023-06-04 18:11:50', 'user5'),(17, '$2a$15$m4CbGGNk2Tw/SeBZJuFqfOHAuDbDdm5pKevdaxKMIWs8g8s5ngxnS', '$2a$15$iCDY.aGgydqWZLqQNQoyCuV9O5Jlxkddaq08hT2CRCDnz9nlhefey', '2023-06-04 18:12:01', 'user6'),(18, '$2a$15$rMDtAQ2M47CpPLX5gTwvJuK0G1Z/2yuYhQznvl2/rI71vDJL6W4Qy', '$2a$15$9RRWtyOBevr/6upb/ISXq.IlJoak/DgS.Pu3jQRtx8F0xU4mVWob6', '2023-06-04 18:12:12', 'user7'),(19, '$2a$15$FUKdDrv.w21VX1vgW342deIgoKQ0FZ7ivQOtSUssnnqUoGZL4mARy', '$2a$15$cLHRSfLNibcMDkIsPkQ2GOg5MKe5zjS24StnsEGEGBQEw2O3V92HK', '2023-06-04 18:12:24', 'user8'),(20, '$2a$15$IoZLrClljzk.eiofaxvx/ec/1FFiPDl8jrwYf5g7LRYIpne6IHJyy', '$2a$15$kx0I.0QNz8G7zYLM4x1HcOVRrshtWbWUiupIy5AvIIVloLEVJ4oTi', '2023-06-04 18:13:25', 'user9'),(21, '$2a$15$uxA0UUnhLSbC8hw.BQEyd.jBKtGT.UcgIyCdZuWEN1f.y96tfxmvu', '$2a$15$mjBA5/sLXSDJndux5aDrMOi5Q0PwiMEzoLsfAjmqerVuWNyLZru2a', '2023-06-04 18:13:41', 'user10');");
      state.close();
   }



}
