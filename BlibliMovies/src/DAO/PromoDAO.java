package DAO;

import Model.Promo;

import java.sql.*;

public class PromoDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public PromoDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Promo getPromo(String id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM promo where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        Promo output;
        if(rs.next()){
            output = new Promo(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getBoolean(5),
                    rs.getInt(6));
        } else{
            output = null;
        }
        return output;
    }
}
