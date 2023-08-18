package db.bean;

import java.sql.ResultSet;

public class ActivityBean extends GenericBean{

    public ActivityBean()
    {}

    public ResultSet getAll()
    {
        try
        {
            return _objConnection.createStatement().executeQuery("select * from activity;");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return null;
        }
    }

    public ResultSet getById(String id)
    {
        try
        {
            return _objConnection.createStatement().executeQuery("select * from activity where id =" + id + ";");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return null;
        }
    }

    public int DeleteById(String id)
    {
        try
        {
            return _objConnection.createStatement().executeUpdate("delete from activity where id =" + id + ";");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
    }

}
