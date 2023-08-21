package db.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReservationBean extends GenericBean {

    public ReservationBean()
    {}

    public ResultSet getAll()
    {
        try
        {
            return _objConnection.createStatement().executeQuery("select * from reservation;");
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
            return _objConnection.createStatement().executeQuery("select * from reservation where id =" + id + ";");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return null;
        }
    }

    public ResultSet getByCustomer(String customer)
    {
        try
        {
            return _objConnection.createStatement().executeQuery("select * from reservation where customer =" + customer + ";");
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return null;
        }
    }

    public int payReservation(int id, String customer)
    {
        try
        {
            String updateQuery = "UPDATE reservation SET paid = 1 WHERE idRoom = ? AND customer LIKE ?";
            PreparedStatement preparedStatement = _objConnection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, customer);

            int temp = preparedStatement.executeUpdate();
            if (temp == 1)
            {
                return _objConnection.createStatement().executeUpdate("update room set busy = 0 where id =" + id + ";");
            }
            else
            {
                return 0;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
    }

    public int cancelReservation(int id, String customer)
    {
        try
        {
            String deleteQuery = "DELETE FROM reservation WHERE idRoom = ? AND customer = ?";
            PreparedStatement preparedStatement = _objConnection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, customer);

            int temp = preparedStatement.executeUpdate();

            if (temp == 1)
            {
                return _objConnection.createStatement().executeUpdate("update room set busy = 0 where id =" + id + ";");
            }
            else
            {
                return 0;
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
    }
    public int makeReservation(int idRoom, String customer)
    {
        String insertQuery = "INSERT INTO reservation (customer, idRoom, paid) VALUES (?, ?, ?)";
        RoomBean room = new RoomBean();
        room.Connect("rti2", "root","Rotko3");
        ResultSet result = room.getById(String.valueOf(idRoom));
        try
        {
            if(result.next()) {
                int busy = result.getInt("busy");
                if (busy == 0)
                {
                    PreparedStatement preparedStatement = _objConnection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, customer);
                    preparedStatement.setInt(2, idRoom);
                    preparedStatement.setInt(3, 0);
                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected == 1)
                    {
                        _objConnection.createStatement().executeUpdate("update room set busy = 1 where id =" + idRoom + ";");
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
                }
                else
                {
                    return 2;
                }
            }
        }
        catch(Exception ex)
        {
            System.out.println("LOG");
            System.out.println("Error SQL: " + ex.getMessage());
            return 0;
        }
        return -1;
    }

}
