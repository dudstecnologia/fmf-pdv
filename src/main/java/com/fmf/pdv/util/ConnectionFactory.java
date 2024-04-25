package com.fmf.pdv.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
   private static final String USERNAME = "root";
   private static final String PASSWORD = "";
   private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pdv";

   public static Connection getConnection() throws Exception{
       Class.forName("com.mysql.cj.jdbc.Driver");

       return DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
   }
}
