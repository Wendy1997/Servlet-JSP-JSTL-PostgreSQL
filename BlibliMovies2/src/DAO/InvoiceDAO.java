package DAO;

import Model.Invoice;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk Invoice
 */
public class InvoiceDAO {

    Connection conn;

    /**
     * Inisialisasi DB
     */
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

    /**
     * Sebuah method untuk mengambil data invoice dari db
     *
     * @param username
     * @param storeid
     * @return
     * @throws SQLException
     */
    public Invoice getInvoice(String username, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where id = ? and storeid = ?");
        ps.setString(1, username);
        ps.setInt(2, storeid);

        ResultSet rs = ps.executeQuery();

        Invoice output;
        if(rs.next()){
            output = new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDouble(7));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method untuk mengambil data invoice dari db
     *
     * @param invoice
     * @return
     * @throws SQLException
     */
    public Invoice getInvoice(Invoice invoice) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where cashierid = ? and storeid = ? and orderdate = ? and totalprice = ?");
        ps.setInt(1,invoice.getAccountID());
        ps.setInt(2,invoice.getStoreID());
        ps.setTimestamp(3,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
        ps.setDouble(4,invoice.getTotalPrice());
        ResultSet rs = ps.executeQuery();

        Invoice output;
        if(rs.next()){
            output = new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDouble(7));
        } else{
            output = null;
        }

        ps.close();
        conn.close();
        return output;
    }

    /**
     * Sebuah method yang akan mengambil seluruh data Invoice
     *
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<Invoice> getAllInvoice(int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where storeid = ? ORDER BY id LIMIT 10 OFFSET ?");
        ps.setInt(1, storeid);
        ps.setInt(2, offset);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }

        ps.close();
        conn.close();
        return invoices;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dari seluruh data Invoice
     *
     * @param storeid
     * @return
     * @throws SQLException
     */
    public int getCountAllInvoice(int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*) from (SELECT * FROM invoice where storeid = ? ORDER BY id) as count");
        ps.setInt(1, storeid);
        ResultSet rs = ps.executeQuery();

                int count = 1;
        if(rs.next()){
            count = rs.getInt(1);
            if(count == 0){
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

    /**
     * Sebuah method yang akan menginput invoice pada db
     *
     * @param invoice
     * @throws SQLException
     */
    public void addInvoice(Invoice invoice) throws SQLException {
        // Pengecekan bila ada member
        if(invoice.getMemberId() == 0){
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invoice (cashierid, storeid, orderdate, totalprice) VALUES (?,?,?,?)");
            ps.setInt(1,invoice.getAccountID());
            ps.setInt(2,invoice.getStoreID());
            ps.setTimestamp(3,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
            ps.setDouble(4,invoice.getTotalPrice());
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        else {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO invoice (memberid, cashierid, storeid, promoid, orderdate, totalprice) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,invoice.getMemberId());
            ps.setInt(2,invoice.getAccountID());
            ps.setInt(3,invoice.getStoreID());
            ps.setInt(4,invoice.getPromoId());
            ps.setTimestamp(5,java.sql.Timestamp.valueOf(invoice.getOrderDate()));
            ps.setDouble(6,invoice.getTotalPrice());
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
    }

    /**
     * Sebuah method untuk mengambil seluruh data daily invoice dari db
     *
     * @param day
     * @param month
     * @param year
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<Invoice> getDailyInvoice(String day, String month, String year, int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (day from orderdate) = ? AND " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ? LIMIT 10 OFFSET ?");
        ps.setDouble(1, Double.parseDouble(day));
        ps.setDouble(2, Double.parseDouble(month));
        ps.setDouble(3, Double.parseDouble(year));
        ps.setInt(4, storeid);
        ps.setInt(5, offset);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }

        ps.close();
        conn.close();
        return invoices;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dan jumlah pendapatan dari seluruh data daily invoice
     *
     * @param day
     * @param month
     * @param year
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<Double> getCountDailyInvoice(String day, String month, String year, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("select count(*), sum(totalprice) from (SELECT * FROM invoice where " +
                "EXTRACT (day from orderdate) = ? AND " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ?) as count");
        ps.setDouble(1, Double.parseDouble(day));
        ps.setDouble(2, Double.parseDouble(month));
        ps.setDouble(3, Double.parseDouble(year));
        ps.setInt(4, storeid);
        ResultSet rs = ps.executeQuery();

        List<Double> count = new ArrayList<>();
        if(rs.next()){
            int temp = rs.getInt(1);
            if(temp == 0){
                count.add((double)temp);
            }
            else if(temp%10 == 0){
                count.add((double)(temp/10));
            } else {
                count.add((double)(temp/10+1));
            }

            count.add(rs.getDouble(2));
        }

        ps.close();
        conn.close();
        return count;
    }

    /**
     * Sebuah method untuk mengambil seluruh data weekly invoice dari db
     *
     * @param week
     * @param year
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<Invoice> getWeeklyInvoice(String week, String year, int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (week from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ? LIMIT 10 OFFSET ?");
        ps.setDouble(1, Double.parseDouble(week));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setInt(3, storeid);
        ps.setInt(4, offset);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }

        ps.close();
        conn.close();
        return invoices;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dan jumlah pendapatan dari seluruh data weekly invoice
     *
     * @param week
     * @param year
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<Double> getCountWeeklyInvoice(String week, String year, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*), sum(totalprice) from (SELECT * FROM invoice where " +
                "EXTRACT (week from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ?) as count");
        ps.setDouble(1, Double.parseDouble(week));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setInt(3, storeid);
        ResultSet rs = ps.executeQuery();

        List<Double> count = new ArrayList<>();
        if(rs.next()){
            int temp = rs.getInt(1);
            if(temp == 0){
                count.add((double)temp);
            }
            else if(temp%10 == 0){
                count.add((double)(temp/10));
            } else {
                count.add((double)(temp/10+1));
            }

            count.add(rs.getDouble(2));
        }

        ps.close();
        conn.close();
        return count;
    }

    /**
     * Sebuah method untuk mengambil seluruh data monthly invoice dari db
     *
     * @param month
     * @param year
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<Invoice> getMonthlyInvoice(String month, String year, int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ? LIMIT 10 OFFSET ?");
        ps.setDouble(1, Double.parseDouble(month));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setInt(3, storeid);
        ps.setInt(4, offset);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }

        ps.close();
        conn.close();
        return invoices;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dan jumlah pendapatan dari seluruh data monthly invoice
     *
     * @param month
     * @param year
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<Double> getCountMonthlyInvoice(String month, String year, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT count(*), sum(totalprice) from (SELECT * FROM invoice where " +
                "EXTRACT (month from orderdate) = ? and " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ?) as count");
        ps.setDouble(1, Double.parseDouble(month));
        ps.setDouble(2, Double.parseDouble(year));
        ps.setInt(3, storeid);
        ResultSet rs = ps.executeQuery();

        List<Double> count = new ArrayList<>();
        if(rs.next()){
            int temp = rs.getInt(1);
            if(temp == 0){
                count.add((double)temp);
            }
            else if(temp%10 == 0){
                count.add((double)(temp/10));
            } else {
                count.add((double)(temp/10+1));
            }

            count.add(rs.getDouble(2));
        }

        ps.close();
        conn.close();
        return count;
    }

    /**
     * Sebuah method untuk mengambil seluruh data yearly invoice dari db
     *
     * @param year
     * @param storeid
     * @param offset
     * @return
     * @throws SQLException
     */
    public List<Invoice> getYearlyInvoice(String year, int storeid, int offset) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM invoice where " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ? LIMIT 10 OFFSET ?");
        ps.setDouble(1, Double.parseDouble(year));
        ps.setInt(2, storeid);
        ps.setInt(3, offset);
        ResultSet rs = ps.executeQuery();

        List<Invoice> invoices = new ArrayList<Invoice>();
        while(rs.next()){
            invoices.add(new Invoice(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6).substring(0,10),
                    rs.getDouble(7)));
        }

        ps.close();
        conn.close();
        return invoices;
    }

    /**
     * Sebuah method yang akan menghitung jumlah halaman dan jumlah pendapatan dari seluruh data yearly invoice
     *
     * @param year
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<Double> getCountYearlyInvoice(String year, int storeid) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("Select count(*), sum(totalprice) from (SELECT * FROM invoice where " +
                "EXTRACT (year from orderdate) = ? and " +
                "storeid = ?) as count");
        ps.setDouble(1, Double.parseDouble(year));
        ps.setInt(2, storeid);
        ResultSet rs = ps.executeQuery();

        List<Double> count = new ArrayList<>();
        if(rs.next()){
            int temp = rs.getInt(1);
            if(temp == 0){
                count.add((double)temp);
            }
            else if(temp%10 == 0){
                count.add((double)(temp/10));
            } else {
                count.add((double)(temp/10+1));
            }

            count.add(rs.getDouble(2));
        }

        ps.close();
        conn.close();
        return count;
    }
}
