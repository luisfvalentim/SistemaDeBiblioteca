package main.java.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
   private static final String USER = "postgres";
   private static final String PASSWORD = "040204";

   public DatabaseConnection() {
   }

   public static Connection getConnection() throws SQLException {
      try {
         return DriverManager.getConnection("jdbc:postgresql://localhost:5432/biblioteca", "postgres", "040204");
      } catch (SQLException var1) {
         System.err.println("Erro ao conectar ao banco de dados: " + var1.getMessage());
         throw var1;
      }
   }
}


