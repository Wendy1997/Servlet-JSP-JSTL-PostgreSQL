package DAO;

import Model.Film;
import Model.Studio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudioDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public StudioDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Studio> getAllStudio(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM studio where storeusername = ?");
        ps.setString(1, storename);

        ResultSet rs = ps.executeQuery();

        List<Studio> studios = new ArrayList<Studio>();
        while(rs.next()){
            studios.add(new Studio(Integer.parseInt(rs.getString(1)),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4)
            ));
        }
        return studios;
    }
}
