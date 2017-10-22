package DAO;

import Model.OrderDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {
    public static final String database = "org.postgresql.Driver";
    public static final String url = "jdbc:postgresql://localhost:5432/bliblimovies";
    public static final String username = "postgres";
    public static final String password = "wendy1997";

    Connection conn;

    public OrderDetailDAO(){

        try {
            Class.forName(database);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e){
            System.out.println(e.getMessage());
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
