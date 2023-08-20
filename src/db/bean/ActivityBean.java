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

    public int updateActivity(int id, int nbrParticipantsInscr)
    {
        ResultSet result = getById(String.valueOf(id));
        try
        {
            if(result.next()) {
                int nbrParticipantsMax = result.getInt("max_participants");
                int nbrRegisteredParticipants = result.getInt("participants_registered");
                if (nbrParticipantsInscr+nbrRegisteredParticipants < nbrParticipantsMax)
                {
                    return _objConnection.createStatement().executeUpdate("update activity set participants_registered = " + (nbrParticipantsInscr+nbrRegisteredParticipants) + " where id =" + id + ";");
                }
                else
                {
                    return 0;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
        return -1;
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
