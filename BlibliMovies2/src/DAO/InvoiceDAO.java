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
                    rs.getDouble(7));
        } else{
            output = null;
        }
        return output;
    }

    public Invoice getInvoice(Invoice invoice) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where accountusername = ? and storeusername = ? and orderdate = ? and totalprice = ?");
        ps.setString(1,invoice.getAccountUsername());
        ps.setString(2,invoice.getStoreName());
        ps.setTimestamp(3,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
        ps.setDouble(4,invoice.getTotalPrice());
        ResultSet rs = ps.executeQuery();

        Invoice output;
        if(rs.next()){
            output = new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDouble(7));
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
                    rs.getDouble(7)));
        }
        return invoices;
    }

    public void addInvoice(Invoice invoice) throws SQLException {
        if(invoice.getMemberId() == 0){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invoice (accountusername, storeusername, orderdate, totalprice) VALUES (?,?,?,?)");
            ps.setString(1,invoice.getAccountUsername());
            ps.setString(2,invoice.getStoreName());
            ps.setTimestamp(3,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
            ps.setDouble(4,invoice.getTotalPrice());
            ps.executeUpdate();
        }
        else {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invoice (memberid, accountusername, storeusername, promoid, orderdate, totalprice) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,invoice.getMemberId());
            ps.setString(2,invoice.getAccountUsername());
            ps.setString(3,invoice.getStoreName());
            ps.setInt(4,invoice.getPromoId());
            ps.setTimestamp(5,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
            ps.setDouble(6,invoice.getTotalPrice());
            ps.executeUpdate();
        }
    }

    public List<Invoice> getDailyInvoice(String day, String month, String year, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (day from orderdate) = ? AND " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeusername = ?");
        ps.setDouble(1, Double.parseDouble(day));
        ps.setDouble(2, Double.parseDouble(month));
        ps.setDouble(3, Double.parseDouble(year));
        ps.setString(4, storename);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }
        return invoices;
    }

    public List<Invoice> getWeeklyInvoice(String week, String year, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (week from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeusername = ?");
        ps.setDouble(1, Double.parseDouble(week));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setString(3, storename);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }
        return invoices;
    }

    public List<Invoice> getMonthlyInvoice(String month, String year, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeusername = ?");
        ps.setDouble(1, Double.parseDouble(month));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setString(3, storename);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }
        return invoices;
    }

    public List<Invoice> getYearlyInvoice(String year, String storename) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeusername = ?");
        ps.setDouble(1, Double.parseDouble(year));
        ps.setString(2, storename);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }
        return invoices;
    }
}
