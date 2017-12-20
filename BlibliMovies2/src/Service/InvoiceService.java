package Service;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceService {

    Invoice getInvoice(String id, String storename) throws SQLException;
    List<Invoice> getAllInvoice(String storename) throws SQLException;
    void addInvoice(Invoice invoice) throws SQLException;
    Invoice getInvoice(Invoice invoice) throws  SQLException;

    List<Invoice> getDailyInvoice(String date, String storename) throws SQLException;
    List<Invoice> getWeeklyInvoice(String date, String storename) throws SQLException;
    List<Invoice> getMonthlyInvoice(String date, String storename) throws SQLException;
    List<Invoice> getYearlyInvoice(String date, String storename) throws SQLException;

    List<OrderDetail> getAllOrderDetail(String id, String storename) throws SQLException;
    void addOrderDetail(OrderDetail orderDetail) throws SQLException;

    Promo getPromo(String id, String storename) throws SQLException;
    Promo getPromo(String storename) throws SQLException;
}
