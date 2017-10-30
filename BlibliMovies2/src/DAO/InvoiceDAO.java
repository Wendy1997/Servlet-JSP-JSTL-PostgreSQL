package DAO;

import Model.Invoice;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InvoiceDAO {

    Connection conn;

    public InvoiceDAO(){

        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            input = classLoader.getResourceAsStream("config.properties");

            // load a properties file
            prop.load(input);

            System.out.println(prop.getProperty("database"));

            Class.forName(prop.getProperty("database"));
            conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Invoice getInvoice(String username, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where id = ? and storeusername = ?");
        ps.setString(1, username);
        ps.setString(2, storename);

        ResultSet rs = ps.executeQuery();

        Invoice output;
        if(rs.next()){
            output = new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getInt(7));
        } else{
            output = null;
        }
        return output;
    }

    public List<Invoice> getAllInvoice(String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where storeusername = ?");
        ps.setString(1, storename);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getInt(7)));
        }
        return invoices;
    }
}
