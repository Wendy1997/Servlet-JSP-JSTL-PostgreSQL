package DAO;

import Model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public InvoiceDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
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
