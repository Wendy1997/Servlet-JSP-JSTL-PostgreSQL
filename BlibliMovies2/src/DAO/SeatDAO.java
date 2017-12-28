package DAO;

import Model.Film;
import Model.Seat;
import Model.Seat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SeatDAO {

    Connection conn;

    public SeatDAO(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Seat getSeat(String seat) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM seat where number = ?");
        ps.setString(1, seat);

        ResultSet rs = ps.executeQuery();

        Seat output;
        if(rs.next()){
            output = new Seat(Integer.parseInt(rs.getString(1)),
                    Integer.parseInt(rs.getString(2)),
                    rs.getString(3)
            );
        } else{
            output = null;
        }

        ps.close();
        return output;
    }
}
