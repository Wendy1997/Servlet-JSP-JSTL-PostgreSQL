package Service;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceService {

    Invoice getInvoice(String id, String storename) throws SQLException;
    List<Invoice> getAllInvoice(String storename) throws SQLException;

    List<OrderDetail> getAllOrderDetail(String id, String storename) throws SQLException;

    Promo getPromo(String id, String storename) throws SQLException;
}
