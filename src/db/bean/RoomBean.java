package db.bean;

import java.sql.ResultSet;

public class RoomBean extends GenericBean {

    public RoomBean()
    {}

    public ResultSet getAll()
    {
        try
        {
            return _objConnection.createStatement().executeQuery("select * from room;");
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
            return _objConnection.createStatement().executeQuery("select * from room where id =" + id + ";");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return null;
        }
    }

    public int updateRoom(int id, int busy)
    {
        ResultSet result = getById(String.valueOf(id));
        try
        {
            if(result.next()) {
                return _objConnection.createStatement().executeUpdate("update room set busy = " + busy + " where id =" + id + ";");
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
        return -1;
    }





}
