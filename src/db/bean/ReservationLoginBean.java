package db.bean;

import java.sql.ResultSet;

public class ReservationLoginBean extends GenericBean {

    public ReservationLoginBean()
    {}

    public boolean Login(String username, String pwd)
    {
        try
        {
            ResultSet result = _objConnection.createStatement().executeQuery("select * from login_reservation where username ='" + username + "' and pwd ='" + pwd + "';");

            if(result.next())
            {
                System.out.println("Login success");
                return true;
            }
            else
            {
                System.out.println("Login failed");
                return false;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return false;
        }
    }
}
