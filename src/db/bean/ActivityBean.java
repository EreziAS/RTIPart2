package db.bean;

import java.sql.ResultSet;

public class ActivityBean extends GenericBean{

    public ActivityBean()
    {}

    public ResultSet getAll()
    {
        try
        {
            ResultSet result = _objConnection.createStatement().executeQuery("select * from activity;");

            return result;
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
            ResultSet result = _objConnection.createStatement().executeQuery("select * from activity where id =" + id + ";");

            return result;
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
            int result = _objConnection.createStatement().executeUpdate("delete from activity where id =" + id + ";");

            return result;
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
    }

}
