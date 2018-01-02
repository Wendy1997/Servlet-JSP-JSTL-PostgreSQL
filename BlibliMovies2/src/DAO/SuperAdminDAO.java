package DAO;

import Model.SuperAdmin;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SuperAdminDAO {

    Connection conn;

    public SuperAdminDAO(){

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

    public SuperAdmin getSuperAdmin(String SuperAdmin) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM superAdmin where username = ?");
        ps.setString(1, SuperAdmin);

        ResultSet rs = ps.executeQuery();

        SuperAdmin output;
        if(rs.next()){
            output = new SuperAdmin(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    public SuperAdmin getSuperAdminTrue(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM superAdmin where username = ? and status = true");
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        SuperAdmin output;
        if(rs.next()){
            output = new SuperAdmin(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getBoolean(4));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    public List<SuperAdmin> getAllSuperAdmin(int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM superAdmin ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, offset);
        ResultSet rs = ps.executeQuery();

        List<SuperAdmin> superAdmins = new ArrayList<SuperAdmin>();
        while(rs.next()){
            superAdmins.add(new SuperAdmin(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return superAdmins;
    }

    public int getCountAllSuperAdmin() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM superadmin ORDER BY id) as count");
        ResultSet rs = ps.executeQuery();

        int count = 1;
        if(rs.next()){
            count = rs.getInt(1);
            if(count < 10){
                count = 1;
            }
            else if(count%10 == 0){
                count = count/10;
            } else {
                count = count/10 + 1;
            }
        }


        ps.close();
        conn.close();
        return count;
    }

    public List<SuperAdmin> getAllSuperAdminTrue() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM superAdmin where status = true ORDER BY id");
        ResultSet rs = ps.executeQuery();

        List<SuperAdmin> superAdmins = new ArrayList<SuperAdmin>();
        while(rs.next()){
            superAdmins.add(new SuperAdmin(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getBoolean(4)));
        }

        ps.close();
        conn.close();
        return superAdmins;
    }

    public void addSuperAdmin(SuperAdmin superAdmin) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO superAdmin (username, password) VALUES (?,?)");
        ps.setString(1,superAdmin.getUsername());
        ps.setString(2, superAdmin.getPassword());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void deleteSuperAdmin(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE superAdmin set status = false where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void retrieveSuperAdmin(int id) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE superAdmin set status = true where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public void updateSuperAdmin(SuperAdmin superAdmin) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE superAdmin set password = ? where id = ?");
        ps.setString(1, superAdmin.getPassword());
        ps.setInt(2, superAdmin.getId());
        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
