package dk.kea.soundsup.utility;

import dk.kea.soundsup.model.User;

import java.sql.*;
import java.util.List;

public class DBConnect
{
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public List<User> allUsersDB;

    public DBConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/soundsupdatabase","root","");
            st = con.createStatement();

        }catch(Exception exc)
        {
            System.out.println("Error:" + exc);
        }
    }

    public List<User> getAllUsersDB()
    {
        try
        {
            String query = "select * from user";
            rs = st.executeQuery(query);

            while(rs.next())
            {
                User user = new User(rs.getInt("id"), rs.getString("firstName"),
                        rs.getString("lastName"), rs.getString("email"));
                allUsersDB.add(user);
            }

        }catch(Exception exc)
        {
            System.out.println("Error:" + exc);
        }

        return allUsersDB;
    }
}
