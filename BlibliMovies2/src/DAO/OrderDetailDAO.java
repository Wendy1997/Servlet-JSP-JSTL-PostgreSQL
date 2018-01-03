package DAO;

import Model.OrderDetail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Sebuah kelas yang menghandle CRUD untuk order detail dari suatu invoice
 */
public class OrderDetailDAO {

    Connection conn;

    /**
     * Inisialisasi DB
     */
    public OrderDetailDAO(){

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
     * Sebuah method yang akan mengambil seluruh data order detail dari suatu invoice
     *
     * @param id
     * @param storeid
     * @return
     * @throws SQLException
     */
    public List<OrderDetail> getAllOrderDetail(String id, int storeid) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM orderdetail where invoiceid = ? AND storeid = ? ORDER BY id");
        ps.setInt(1, Integer.parseInt(id));
        ps.setInt(2, storeid);
        ResultSet rs = ps.executeQuery();

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        while(rs.next()){
            orderDetails.add(new OrderDetail(rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getBoolean(7)));
        }

        ps.close();
        conn.close();
        return orderDetails;
    }

    /**
     * Sebuah method yang akan menginput order detail pada db
     *
     * @param orderDetail
     * @throws SQLException
     */
    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO orderdetail (invoiceid, storeid, itemname, quantity, price, discountstatus) VALUES (?,?,?,?,?,?)");
        ps.setInt(1, orderDetail.getInvoiceId());
        ps.setInt(2, orderDetail.getStoreID());
        ps.setString(3, orderDetail.getItemName());
        ps.setInt(4, orderDetail.getQuantity());
        ps.setInt(5, orderDetail.getPrice());
        ps.setBoolean(6, orderDetail.isDiscountStatus());

        ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
