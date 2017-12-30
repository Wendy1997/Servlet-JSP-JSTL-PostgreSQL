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
    int getCountDailyInvoice(String date, int storeid) throws SQLException;
    int getCountWeeklyInvoice(String date, int storeid) throws SQLException;
    int getCountMonthlyInvoice(String date, int storeid) throws SQLException;
    int getCountYearlyInvoice(String date, int storeid) throws SQLException;

    List<OrderDetail> getAllOrderDetail(String id, int storeid) throws SQLException;
    void addOrderDetail(OrderDetail orderDetail) throws SQLException;

    Promo getPromo(String id, int storeid) throws SQLException;
    Promo getPromoTrue(String id, int storeid) throws SQLException;
    Promo getPromo(int storeid) throws SQLException;
    Promo getPromoTrue(int storeid) throws SQLException;
}
