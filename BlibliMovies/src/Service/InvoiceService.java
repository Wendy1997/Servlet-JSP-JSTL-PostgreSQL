package Service;

import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public interface InvoiceService {

    Invoice getInvoice(String id) throws SQLException;
    List<Invoice> getAllInvoice() throws SQLException;

    List<OrderDetail> getAllOrderDetail(String id) throws SQLException;

    Promo getPromo(String id) throws SQLException;
}
