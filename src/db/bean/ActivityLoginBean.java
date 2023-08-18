package db.bean;

import java.sql.ResultSet;

public class ActivityLoginBean extends GenericBean{

        public ActivityLoginBean()
        {}

        public ResultSet Login(String username, String pwd)
        {
            try
            {
                ResultSet result = _objConnection.createStatement().executeQuery("select * from login_activity where username ='" + username + "' and pwd ='" + pwd + "';");

                return result;
            }
            catch(Exception ex)
            {
                System.out.println("Error SQL: " + ex.getMessage());
                return null;
            }
        }
}
