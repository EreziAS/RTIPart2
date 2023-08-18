package db.bean;

import java.sql.*;
public class GenericBean {

    public Connection _objConnection;

    public GenericBean()
    {}

    public void Connect(String BD, String login, String password)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            _objConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD, login, password);
        }
        catch (SQLException ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Error Driver not found: " + e.getMessage());
        }
    }
}
