package Service;

import DAO.InvoiceDAO;
import DAO.OrderDetailDAO;
import DAO.PromoDAO;
import Model.Invoice;
import Model.OrderDetail;
import Model.Promo;

import java.sql.SQLException;
import java.util.List;

public class InvoiceServiceDatabase implements InvoiceService {
    InvoiceDAO invoiceDAO = new InvoiceDAO();
    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    PromoDAO promoDAO = new PromoDAO();

    @Override
    public Invoice getInvoice(String id, String storename) throws SQLException {
        return invoiceDAO.getInvoice(id, storename);
    }

    @Override
    public List<Invoice> getAllInvoice(String storename) throws SQLException {
        return invoiceDAO.getAllInvoice(storename);
    }

    @Override
    public List<OrderDetail> getAllOrderDetail(String id, String storename) throws SQLException {
        return orderDetailDAO.getAllOrderDetail(id, storename);
    }

    @Override
    public Promo getPromo(String id, String storename) throws SQLException{
        return promoDAO.getPromo(id, storename);
    }
}
