package Test;

import db.bean.GenericBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        int i = 0;
        GenericBean bean = new GenericBean();
        bean.Connect("rti2", "root", "Rotko3");

        System.out.println("Connection: " + bean._objConnection);
        try {

            while (i == 0) {
                String _temp = "select * from activity;";
                ResultSet res = bean._objConnection.createStatement().executeQuery(_temp);
                //Print the result
                while (res.next()) {
                    System.out.println("Num: " + res.getString("id"));
                    System.out.println("Type: " + res.getString("title"));
                    System.out.println("NbrParticipantsMax: " + res.getString("max_participants"));
                    System.out.println("NbrParticipantsInscr: " + res.getString("participants_registered"));
                    System.out.println("Duree: " + res.getString("duration"));
                    System.out.println("Prix: " + res.getString("price"));

                }
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

