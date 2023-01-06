package com.example.ourwedding.CONFIG;

import javax.management.Query;
import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcCon {
//    InvitedPerson invitedPerson;
    static String url="jdbc:mysql://localhost:3306/weddingPage?useSSL=false";
    static String username="root";
    static String password="SorinSiRaluca";
    public static void main(String[] args) {
        try{
            System.out.println("Connecting to Db"+url);
Connection myCon= DriverManager.getConnection(url,username,password);
            System.out.println("Connextion successful");

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
