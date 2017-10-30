package DAO;

import Model.OrderDetail;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class OrderDetailDAO {

    Connection conn;

    public OrderDetailDAO(){

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

    public List<OrderDetail> getAllOrderDetail(String id, String storename) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM orderdetail where invoiceid = ? AND storeusername = ?");
        ps.setString(1, id);
        ps.setString(2, storename);
        ResultSet rs = ps.executeQuery();

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        while(rs.next()){
            orderDetails.add(new OrderDetail(rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getBoolean(7)));
        }
        return orderDetails;
    }

}