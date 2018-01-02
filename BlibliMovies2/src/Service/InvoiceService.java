package Service;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceService {

    Invoice getInvoice(String id, int storeid) throws SQLException;
    List<Invoice> getAllInvoice(int storeid, int offset) throws SQLException;
    int getCountAllInvoice(int storeid) throws SQLException;
    void addInvoice(Invoice invoice) throws SQLException;
    Invoice getInvoice(Invoice invoice) throws  SQLException;

    List<Invoice> getDailyInvoice(String date, int storeid, int offset) throws SQLException;
    List<Invoice> getWeeklyInvoice(String date, int storeid, int offset) throws SQLException;
    List<Invoice> getMonthlyInvoice(String date, int storeid, int offset) throws SQLException;
    List<Invoice> getYearlyInvoice(String date, int storeid, int offset) throws SQLException;
    List<Double> getCountDailyInvoice(String date, int storeid) throws SQLException;
    List<Double> getCountWeeklyInvoice(String date, int storeid) throws SQLException;
    List<Double> getCountMonthlyInvoice(String date, int storeid) throws SQLException;
    List<Double> getCountYearlyInvoice(String date, int storeid) throws SQLException;

    List<OrderDetail> getAllOrderDetail(String id, int storeid) throws SQLException;
    void addOrderDetail(OrderDetail orderDetail) throws SQLException;

    Promo getPromo(int storeid) throws SQLException;
    void addPromo(Promo promo) throws SQLException;
}
