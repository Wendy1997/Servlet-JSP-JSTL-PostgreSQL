package DAO;

import Model.StoreAccount;

import java.sql.*;

public class StoreAccountDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public StoreAccountDAO(){
        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public StoreAccount getStoreAccount(String storeAccount) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM storeaccount where username = ?");
        ps.setString(1, storeAccount);

        ResultSet rs = ps.executeQuery();

        StoreAccount output;
        if(rs.next()){
            output = new StoreAccount(rs.getString(1), rs.getString(2), rs.getString(3));
        } else{
            output = null;
        }
        return output;
    }
}
