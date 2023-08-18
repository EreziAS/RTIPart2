package Test;

import db.bean.ActivityBean;
import db.bean.ActivityLoginBean;
import db.bean.GenericBean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        int i = 0;
        ActivityLoginBean bean = new ActivityLoginBean();
        bean.Connect("rti2", "root", "Rotko3");

        System.out.println("Connection: " + bean._objConnection);
        try {

            while (i == 0) {
                ResultSet rs = bean.Login("Alexis", "Aleixs");
                if(rs.next()){
                    System.out.println("Login success");
                    System.out.println("Username: " + rs.getString("username") + " Password: " + rs.getString("pwd"));
                }
                else
                    System.out.println("Login failed");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

